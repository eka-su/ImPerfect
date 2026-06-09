package com.example.imperfect.feature.analysis.data.repository

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.imperfect.core.database.dao.AnalysisDao
import com.example.imperfect.core.database.dao.PhotoDao
import com.example.imperfect.core.database.entity.MultiImageAnalysisPhotoEntity
import com.example.imperfect.feature.analysis.data.mapper.AnalysisEntityMapper
import com.example.imperfect.feature.analysis.data.mapper.toDomain
import com.example.imperfect.feature.analysis.data.mapper.toFile
import com.example.imperfect.feature.analysis.data.remote.AnalysisRemoteSource
import com.example.imperfect.feature.analysis.domain.model.AnalysisResult
import com.example.imperfect.feature.analysis.domain.model.Detection
import com.example.imperfect.feature.analysis.domain.model.ImageAnalysis
import com.example.imperfect.feature.analysis.domain.model.Summary
import com.example.imperfect.feature.analysis.domain.repository.AnalysisRepository
import com.example.imperfect.feature.photo.domain.model.Photo
import com.example.imperfect.feature.photo.domain.repository.LookupRepository

class AnalysisRepositoryImpl(
    private val context: Context,
    private val remoteSource: AnalysisRemoteSource,
    private val lookupRepository: LookupRepository,
    private val analysisDao: AnalysisDao,
    private val photoDao: PhotoDao
) : AnalysisRepository {

    override suspend fun analyzePhotos(
        photos: List<Photo>
    ): AnalysisResult {

        val types = lookupRepository.getPhotoTypes()

        val frontType = types.first {
            it.code == "FRONT"
        }

        val leftType = types.first {
            it.code == "LEFT"
        }

        val rightType = types.first {
            it.code == "RIGHT"
        }

        val front = photos.first {
            it.viewTypeId == frontType.id
        }

        val left = photos.first {
            it.viewTypeId == leftType.id
        }

        val right = photos.first {
            it.viewTypeId == rightType.id
        }

        val response = remoteSource.analyze(
            front = Uri.parse(front.filePath).toFile(context),
            left = Uri.parse(left.filePath).toFile(context),
            right = Uri.parse(right.filePath).toFile(context)
        )

        return response.toDomain()
    }

    override suspend fun saveAnalysis(
        diaryId: Int,
        photos: List<Photo>,
        result: AnalysisResult
    ): Long {

        // MULTI ANALYSIS
        val multiAnalysisId = analysisDao.insertMultiAnalysis(
            AnalysisEntityMapper.toMultiEntity(
                diaryId = diaryId,
                result = result
            )
        ).toInt()

        Log.d(
            "DB",
            "multiAnalysis created id=$multiAnalysisId"
        )

        // PHOTO ANALYSES
        photos.forEachIndexed { index, photo ->

            val image = result.images[index]

            // PHOTO ANALYSIS
            val photoAnalysisId = analysisDao.insertPhotoAnalysis(
                AnalysisEntityMapper.toPhotoAnalysis(
                    photoId = photo.id
                )
            ).toInt()

            Log.d(
                "DB",
                "photoAnalysis created id=$photoAnalysisId"
            )

            // LINK TABLE
            analysisDao.insertMultiImageAnalysisPhoto(
                MultiImageAnalysisPhotoEntity(
                    multiAnalysisId = multiAnalysisId,
                    photoAnalysisId = photoAnalysisId
                )
            )

            // SKIN ANALYSIS
            val skinAnalysisId = analysisDao.insertSkinAnalysis(
                AnalysisEntityMapper.toSkin(
                    photoAnalysisId = photoAnalysisId,
                    image = image
                )
            ).toInt()

            // ISSUES
            analysisDao.insertIssues(
                AnalysisEntityMapper.toIssues(
                    skinId = skinAnalysisId,
                    detections = image.detections
                )
            )

            // SUMMARY
            analysisDao.insertSummary(
                listOf(
                    AnalysisEntityMapper.toSummary(
                        skinId = skinAnalysisId,
                        image = image
                    )
                )
            )
        }

        Log.d(
            "DB",
            "saveAnalysis COMPLETE id=$multiAnalysisId"
        )

        return multiAnalysisId.toLong()
    }

    override suspend fun deleteAnalysis(
        multiAnalysisId: Int
    ) {
        analysisDao.deleteFullAnalysis(
            multiAnalysisId
        )
    }

    override suspend fun getSavedAnalysis(
        analysisId: Int
    ): Pair<AnalysisResult, List<Photo>> {

        val multi = analysisDao.getMultiAnalysisById(
            analysisId
        ) ?: error("Analysis not found")

        val photoAnalyses = analysisDao.getPhotoAnalyses(
            analysisId
        )

        val photoAnalysisIds = photoAnalyses.map {
            it.id
        }

        val skinAnalyses = analysisDao.getSkinAnalyses(
            photoAnalysisIds
        )

        val photos = photoDao.getByIds(
            photoAnalyses.map {
                it.photoId
            }
        )

        val images = skinAnalyses.map { skin ->

            val issues = analysisDao.getIssues(
                skin.id
            )

            val detections = issues.map {

                Detection(
                    classId = it.classId,
                    x = it.x,
                    y = it.y,
                    width = it.width,
                    height = it.height,
                    confidence = it.confidence
                )
            }

            ImageAnalysis(
                view = "",
                severity = skin.severity,
                predictedCount = skin.predictedCount,
                severityProbabilities = emptyList(),
                detectedCount = skin.detectedCount,
                finalSeverity = skin.finalSeverity,
                finalCount = skin.finalCount,
                confidenceScore = skin.confidenceScore,
                detections = detections
            )
        }

        val result = AnalysisResult(

            summary = Summary(
                finalSeverity = multi.finalSeverity,
                averageAcneCount = multi.averageAcneCount,
                totalDetections = multi.totalDetections,
                averageConfidence = multi.averageConfidence
            ),

            images = images
        )

        return result to photos.map {

            Photo(
                id = it.id,
                diaryId = it.diaryId,
                filePath = it.filePath,
                viewTypeId = it.viewTypeId,
                createdAt = it.createdAt
            )
        }
    }
}