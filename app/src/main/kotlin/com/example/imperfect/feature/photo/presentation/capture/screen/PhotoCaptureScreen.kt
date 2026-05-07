package com.example.imperfect.feature.photo.presentation.capture.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imperfect.feature.photo.presentation.capture.camera.rememberCamera
import com.example.imperfect.feature.photo.presentation.capture.component.CameraPreview
import com.example.imperfect.feature.photo.presentation.capture.component.CaptureButton
import com.example.imperfect.feature.photo.presentation.capture.component.StepOverlay
import com.example.imperfect.feature.photo.presentation.capture.component.TopBar

@Composable
fun PhotoCaptureScreen(
    step: Int,
    onTakePhoto: (String) -> Unit,
    onBack: () -> Unit
) {
    val camera = rememberCamera(onTakePhoto)

    Box(Modifier.fillMaxSize()) {

        CameraPreview(camera.previewView)

        StepOverlay(step)

        CaptureButton(
            isTakingPhoto = camera.isTakingPhoto,
            onClick = camera.takePhoto,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(32.dp)
        )

        TopBar(
            step = step,
            onBack = onBack,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}





