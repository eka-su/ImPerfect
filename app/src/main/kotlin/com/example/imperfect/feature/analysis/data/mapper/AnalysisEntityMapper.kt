package com.example.imperfect.feature.analysis.data.mapper

import com.example.imperfect.core.database.entity.MultiImageAnalysisEntity
import com.example.imperfect.core.database.entity.PhotoAnalysisEntity
import com.example.imperfect.core.database.entity.SkinAnalysisEntity
import com.example.imperfect.core.database.entity.SkinIssueEntity
import com.example.imperfect.core.database.entity.SkinIssueSummaryEntity
import com.example.imperfect.feature.analysis.domain.model.AnalysisResult
import com.example.imperfect.feature.analysis.domain.model.Detection
import com.example.imperfect.feature.analysis.domain.model.ImageAnalysis
import com.google.gson.Gson

object AnalysisEntityMapper {

    fun toMultiEntity(diaryId: Int, result: AnalysisResult) =
        MultiImageAnalysisEntity(
            diaryId = diaryId,
            finalSeverity = result.summary.finalSeverity,
            averageAcneCount = result.summary.averageAcneCount,
            totalDetections = result.summary.totalDetections,
            averageConfidence = result.summary.averageConfidence,
            createdAt = System.currentTimeMillis().toString()
        )

    fun toPhotoAnalysis(photoId: Int) =
        PhotoAnalysisEntity(
            photoId = photoId,
            statusId = 2,
            createdAt = System.currentTimeMillis().toString()
        )

    fun toSkin(photoAnalysisId: Int, image: ImageAnalysis) =
        SkinAnalysisEntity(
            photoAnalysisId = photoAnalysisId,
            severity = image.severity,
            predictedCount = image.predictedCount,
            severityProbabilities = Gson().toJson(image.severityProbabilities),
            detectedCount = image.detectedCount,
            finalSeverity = image.finalSeverity,
            finalCount = image.finalCount,
            confidenceScore = image.confidenceScore,
            heatmapPath = null,
            annotatedImagePath = null,
            createdAt = System.currentTimeMillis().toString()
        )

    fun toIssues(skinId: Int, detections: List<Detection>) =
        detections.map {
            SkinIssueEntity(
                analysisId = skinId,
                classId = it.classId,
                type = null,
                x = it.x,
                y = it.y,
                width = it.width,
                height = it.height,
                confidence = it.confidence,
                source = "YOLO"
            )
        }

    fun toSummary(skinId: Int, image: ImageAnalysis) =
        SkinIssueSummaryEntity(
            analysisId = skinId,
            type = image.finalSeverity,
            count = image.finalCount
        )
}