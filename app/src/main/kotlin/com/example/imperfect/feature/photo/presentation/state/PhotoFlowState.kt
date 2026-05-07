package com.example.imperfect.feature.photo.presentation.state

import com.example.imperfect.feature.photo.domain.model.Photo

data class PhotoFlowState(
    val stage: FlowStage = FlowStage.SOURCE,
    val step: Int = 1,
    val photos: List<Photo> = emptyList(),
    val currentPhoto: Photo? = null,
    val source: Source = Source.CAMERA,
    val showExitDialog: Boolean = false
)

