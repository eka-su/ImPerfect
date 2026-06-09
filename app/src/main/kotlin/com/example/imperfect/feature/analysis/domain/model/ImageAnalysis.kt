package com.example.imperfect.feature.analysis.domain.model

data class ImageAnalysis(
    val view: String,
    val severity: String,
    val predictedCount: Double,
    val severityProbabilities: List<Double>,
    val detectedCount: Int,
    val finalSeverity: String,
    val finalCount: Int,
    val confidenceScore: Double,
    val detections: List<Detection>
)