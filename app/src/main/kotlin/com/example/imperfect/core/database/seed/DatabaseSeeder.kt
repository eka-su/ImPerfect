package com.example.imperfect.core.database.seed

import com.example.imperfect.core.database.dao.AnalysisContextDao
import com.example.imperfect.core.database.dao.AnalysisDao
import com.example.imperfect.core.database.dao.DiaryDao
import com.example.imperfect.core.database.dao.PhotoDao
import com.example.imperfect.core.database.dao.UserDao
import com.example.imperfect.core.database.entity.AnalysisContextEntity
import com.example.imperfect.core.database.entity.AppUserEntity
import com.example.imperfect.core.database.entity.DiaryPhotoEntity
import com.example.imperfect.core.database.entity.MultiImageAnalysisEntity
import com.example.imperfect.core.database.entity.MultiImageAnalysisPhotoEntity
import com.example.imperfect.core.database.entity.PhotoAnalysisEntity
import com.example.imperfect.core.database.entity.SkinAnalysisEntity
import com.example.imperfect.core.database.entity.SkinDiaryDayEntity
import com.example.imperfect.core.database.entity.SkinIssueEntity
import com.example.imperfect.core.database.entity.SkinIssueSummaryEntity
import java.time.LocalDate

class DatabaseSeeder(
    private val userDao: UserDao,
    private val diaryDao: DiaryDao,
    private val photoDao: PhotoDao,
    private val analysisDao: AnalysisDao,
    private val contextDao: AnalysisContextDao,
) {

    suspend fun seed() {

        val userId = userDao.insert(
            AppUserEntity(
                name = "Test User",
                email = "test@test.com",
                passwordHash = "hash",
                skinType = "normal",
                createdAt = "2026-01-01"
            )
        ).toInt()

        createFakeDay(
            userId = userId,
            date = LocalDate.now().minusDays(1).toString(),
            severity = "Moderate",
            detections = 12
        )

        createFakeDay(
            userId = userId,
            date = LocalDate.now().minusDays(2).toString(),
            severity = "Mild",
            detections = 5
        )
    }

    private suspend fun createFakeDay(
        userId: Int,
        date: String,
        severity: String,
        detections: Int
    ) {

        val diaryId = diaryDao.insertDay(
            SkinDiaryDayEntity(
                userId = userId,
                date = date
            )
        ).toInt()

        val frontPhotoId = photoDao.insertPhoto(
            DiaryPhotoEntity(
                diaryId = diaryId,
                filePath = "",
                viewTypeId = 1,
                createdAt = date
            )
        ).toInt()

        val leftPhotoId = photoDao.insertPhoto(
            DiaryPhotoEntity(
                diaryId = diaryId,
                filePath = "",
                viewTypeId = 2,
                createdAt = date
            )
        ).toInt()

        val rightPhotoId = photoDao.insertPhoto(
            DiaryPhotoEntity(
                diaryId = diaryId,
                filePath = "",
                viewTypeId = 3,
                createdAt = date
            )
        ).toInt()

        val multiAnalysisId =
            analysisDao.insertMultiAnalysis(
                MultiImageAnalysisEntity(
                    diaryId = diaryId,
                    finalSeverity = severity,
                    averageAcneCount = detections.toDouble(),
                    totalDetections = detections,
                    averageConfidence = 0.94,
                    createdAt = date
                )
            ).toInt()

        listOf(
            frontPhotoId,
            leftPhotoId,
            rightPhotoId
        ).forEach { photoId ->

            val photoAnalysisId =
                analysisDao.insertPhotoAnalysis(
                    PhotoAnalysisEntity(
                        photoId = photoId,
                        statusId = 2,
                        createdAt = date
                    )
                ).toInt()

            analysisDao.insertMultiImageAnalysisPhoto(
                MultiImageAnalysisPhotoEntity(
                    multiAnalysisId = multiAnalysisId,
                    photoAnalysisId = photoAnalysisId
                )
            )

            val skinAnalysisId =
                analysisDao.insertSkinAnalysis(
                    SkinAnalysisEntity(
                        photoAnalysisId = photoAnalysisId,
                        severity = severity,
                        predictedCount = detections.toDouble(),
                        severityProbabilities = "{}",
                        detectedCount = detections,
                        finalSeverity = severity,
                        finalCount = detections,
                        confidenceScore = 0.94,
                        heatmapPath = null,
                        annotatedImagePath = null,
                        createdAt = date
                    )
                ).toInt()

            analysisDao.insertIssues(
                listOf(
                    SkinIssueEntity(
                        analysisId = skinAnalysisId,
                        classId = 0,
                        type = "Acne",
                        x = 100.0,
                        y = 100.0,
                        width = 40.0,
                        height = 40.0,
                        confidence = 0.96,
                        source = "seed"
                    )
                )
            )

            analysisDao.insertSummary(
                listOf(
                    SkinIssueSummaryEntity(
                        analysisId = skinAnalysisId,
                        type = "Acne",
                        count = detections
                    )
                )
            )
        }

        contextDao.insert(
            AnalysisContextEntity(
                multi_analysis_id = multiAnalysisId,
                has_makeup = 0,
                note = "Fake analysis for $date"
            )
        )
    }

}