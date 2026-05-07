package com.example.imperfect.feature.photo.presentation.flow.component

import androidx.compose.runtime.Composable
import com.example.imperfect.feature.photo.presentation.capture.screen.PhotoCaptureScreen
import com.example.imperfect.feature.photo.presentation.gallery.GalleryPickerScreen
import com.example.imperfect.feature.photo.presentation.state.FlowStage
import com.example.imperfect.feature.photo.presentation.state.PhotoFlowState
import com.example.imperfect.feature.photo.presentation.state.Source
import com.example.imperfect.feature.photo.presentation.viewmodel.PhotoViewModel

@Composable
fun CaptureScreen(
    state: PhotoFlowState,
    viewModel: PhotoViewModel
) {
    if (state.source == Source.CAMERA) {
        PhotoCaptureScreen(
            step = state.step,
            onTakePhoto = {
                viewModel.addPhoto(it)
                viewModel.setStage(FlowStage.PREVIEW)
            },
            onBack = {
                viewModel.setStage(FlowStage.GUIDE)
            }
        )
    } else {
        GalleryPickerScreen(
            onSelect = {
                viewModel.addPhoto(it)
                viewModel.setStage(FlowStage.PREVIEW)
            }
        )
    }
}
