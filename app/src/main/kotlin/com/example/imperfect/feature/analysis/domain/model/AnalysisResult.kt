package com.example.imperfect.feature.analysis.domain.model

data class AnalysisResult(
    val summary: Summary,
    val images: List<ImageAnalysis>
)