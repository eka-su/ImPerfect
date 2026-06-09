package com.example.imperfect.feature.photo.presentation.state

import com.example.imperfect.feature.photo.domain.model.Photo
import com.example.imperfect.feature.photo.domain.model.PhotoViewType

data class PhotoFlowState(
    val stage: FlowStage = FlowStage.SOURCE,
    val step: Int = 0,
    val photos: List<Photo> = emptyList(),
    val currentPhoto: Photo? = null,
    val source: Source = Source.NONE,  //NONE CAMERA
    val showExitDialog: Boolean = false,
    val photoOrder: List<PhotoViewType> = emptyList(),

)

