package com.example.imperfect.feature.analysis.presentation.state

import com.example.imperfect.feature.analysis.domain.model.AnalysisResult
import com.example.imperfect.feature.photo.domain.model.Photo

data class AnalysisState(
    val isLoading: Boolean = false,
    val result: AnalysisResult? = null,
    val photos: List<Photo> = emptyList(),
    val error: String? = null
)