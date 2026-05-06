package com.example.imperfect.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction
import com.example.imperfect.core.database.entity.MultiImageAnalysisEntity
import com.example.imperfect.core.database.entity.MultiImageAnalysisPhotoEntity
import com.example.imperfect.core.database.entity.PhotoAnalysisEntity
import com.example.imperfect.core.database.entity.SkinAnalysisEntity
import com.example.imperfect.core.database.entity.SkinIssueEntity
import com.example.imperfect.core.database.entity.SkinIssueSummaryEntity

@Dao
interface AnalysisDao {

    @Insert
    suspend fun insertMultiAnalysis(entity: MultiImageAnalysisEntity): Long

    @Insert
    suspend fun insertPhotoAnalysis(entity: PhotoAnalysisEntity): Long

    @Insert
    suspend fun insertSkinAnalysis(entity: SkinAnalysisEntity): Long

    @Insert
    suspend fun insertMultiImageAnalysisPhoto(entity: MultiImageAnalysisPhotoEntity): Long

    @Insert
    suspend fun insertIssues(list: List<SkinIssueEntity>)

    @Insert
    suspend fun insertSummary(list: List<SkinIssueSummaryEntity>)

    @Transaction
    suspend fun insertFullAnalysis(
        multi: MultiImageAnalysisEntity,
        photoAnalyses: List<PhotoAnalysisEntity>,
        skinAnalyses: List<SkinAnalysisEntity>,
        issues: List<SkinIssueEntity>,
        summaries: List<SkinIssueSummaryEntity>
    ) {
        val multiId = insertMultiAnalysis(multi)

        val photoIds = photoAnalyses.map {
            insertPhotoAnalysis(it)
        }

        photoIds.forEach { photoId ->
            insertMultiImageAnalysisPhoto(
                MultiImageAnalysisPhotoEntity(
                    multiAnalysisId = multiId.toInt(),
                    photoAnalysisId = photoId.toInt()
                )
            )
        }

        val skinIds = skinAnalyses.mapIndexed { index, skin ->
            insertSkinAnalysis(
                skin.copy(photoAnalysisId = photoIds[index].toInt())
            )
        }

        val fixedIssues = issues.mapIndexed { index, issue ->
            issue.copy(analysisId = skinIds[index].toInt())
        }

        val fixedSummaries = summaries.mapIndexed { index, summary ->
            summary.copy(analysisId = skinIds[index].toInt())
        }

        insertIssues(fixedIssues)
        insertSummary(fixedSummaries)
    }
}