package com.example.imperfect.feature.photo.presentation.preview.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imperfect.feature.photo.presentation.preview.component.PhotoFrame
import com.example.imperfect.feature.photo.presentation.preview.component.PreviewActions
import com.example.imperfect.feature.photo.presentation.preview.component.PreviewHeader
import com.example.imperfect.feature.photo.presentation.state.Source

@Composable
fun PhotoPreviewScreen(
    photoPath: String,
    step: Int,
    isValid: Boolean,
    error: String?,
    source: Source,
    onConfirm: () -> Unit,
    onRetake: () -> Unit,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {

        Spacer(Modifier.height(16.dp))


        PreviewHeader(step, onClose)

        Spacer(Modifier.height(16.dp))

        PhotoFrame(
            photoPath = photoPath,
            isValid = isValid,
            error = error,
            source = source
        )

        Spacer(Modifier.height(16.dp))

        PreviewActions(
            onRetake = onRetake,
            onConfirm = onConfirm
        )

        Spacer(Modifier.height(20.dp))
    }
}








