package com.example.imperfect.feature.analysis.data.mapper

import com.example.imperfect.feature.analysis.data.remote.dto.AnalyzeMultiResponse
import com.example.imperfect.feature.analysis.domain.model.AnalysisResult
import com.example.imperfect.feature.analysis.domain.model.Detection
import com.example.imperfect.feature.analysis.domain.model.ImageAnalysis
import com.example.imperfect.feature.analysis.domain.model.Summary

fun AnalyzeMultiResponse.toDomain(): AnalysisResult {

    return AnalysisResult(

        summary = Summary(
            finalSeverity = summary.finalSeverity,
            averageAcneCount = summary.averageAcneCount,
            totalDetections = summary.totalDetections,
            averageConfidence = summary.averageConfidence
        ),

        images = perImageResults.map {

            ImageAnalysis(

                view = it.view,

                severity = it.result.classification.severity,

                predictedCount = it.result.classification.predictedCount,

                severityProbabilities =
                    it.result.classification.severityProbabilities,

                detectedCount =
                    it.result.localization.detectedCount,

                finalSeverity =
                    it.result.ensemble.finalSeverity,

                finalCount =
                    it.result.ensemble.finalCount,

                confidenceScore =
                    it.result.ensemble.confidenceScore,

                detections =
                    it.result.localization.detections.map { dto ->

                        Detection(
                            classId = dto.classId,

                            x = dto.bbox[0],
                            y = dto.bbox[1],
                            width = dto.bbox[2],
                            height = dto.bbox[3],

                            confidence = dto.confidence
                        )
                    }
            )
        }
    )
}