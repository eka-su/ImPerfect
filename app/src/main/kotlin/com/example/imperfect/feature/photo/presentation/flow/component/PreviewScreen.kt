package com.example.imperfect.feature.photo.presentation.flow.component

import androidx.compose.runtime.Composable
import com.example.imperfect.feature.photo.presentation.preview.screen.PhotoPreviewScreen
import com.example.imperfect.feature.photo.presentation.state.FlowStage
import com.example.imperfect.feature.photo.presentation.state.PhotoFlowState
import com.example.imperfect.feature.photo.presentation.viewmodel.PhotoViewModel

@Composable
fun PreviewScreen(
    state: PhotoFlowState,
    viewModel: PhotoViewModel
) {
    PhotoPreviewScreen(
        photoPath = state.currentPhoto?.filePath.orEmpty(),
        step = state.step,
        isValid = true,
        error = null,

        onRetake = {
            viewModel.setStage(FlowStage.CAPTURE)
        },

        onConfirm = {
            if (viewModel.canFinish()) {
                viewModel.saveAll(1)
            } else {
                viewModel.nextStep()
                viewModel.setStage(FlowStage.CAPTURE)
            }
        },

        onClose = {
            viewModel.onPreviewBackClicked()
        }
    )
}
