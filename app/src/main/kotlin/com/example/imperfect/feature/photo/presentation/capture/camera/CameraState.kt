package com.example.imperfect.feature.photo.presentation.capture.camera

import androidx.camera.view.PreviewView

data class CameraState(
    val previewView: PreviewView,
    val isTakingPhoto: Boolean,
    val takePhoto: () -> Unit
)