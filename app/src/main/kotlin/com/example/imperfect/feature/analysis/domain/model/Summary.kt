package com.example.imperfect.feature.analysis.domain.model

data class Summary(
    val finalSeverity: String,
    val averageAcneCount: Double,
    val totalDetections: Int,
    val averageConfidence: Double
)