package com.example.imperfect.feature.analysis.presentation.event

sealed interface AnalysisEvent {
    data object OpenPhotoFlow : AnalysisEvent
}