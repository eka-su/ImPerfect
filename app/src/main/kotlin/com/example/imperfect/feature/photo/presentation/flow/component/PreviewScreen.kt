package com.example.imperfect.feature.photo.presentation.flow.component

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.imperfect.feature.photo.presentation.preview.screen.PhotoPreviewScreen
import com.example.imperfect.feature.photo.presentation.state.FlowStage
import com.example.imperfect.feature.photo.presentation.state.PhotoFlowState
import com.example.imperfect.feature.photo.presentation.viewmodel.PhotoViewModel

private const val TAG = "PHOTO_PREVIEW"

@Composable
fun PreviewScreen(
    state: PhotoFlowState,
    viewModel: PhotoViewModel,
) {

    LaunchedEffect(state) {
        Log.d(TAG, """
            ================ PREVIEW COMPOSE ================
            step = ${state.step}
            stage = ${state.stage}
            source = ${state.source}
            photos = ${state.photos.size}
            currentPhoto = ${state.currentPhoto?.filePath}
            isValid = ${viewModel.canFinish()}
            ==================================================
        """.trimIndent())
    }

    LaunchedEffect(state.currentPhoto?.filePath) {
        Log.d(TAG, "CURRENT PHOTO CHANGED = ${state.currentPhoto?.filePath}")
    }

    LaunchedEffect(state.step) {
        Log.d(TAG, "STEP CHANGED = ${state.step}")
    }

    PhotoPreviewScreen(
        photoPath = state.currentPhoto?.filePath.orEmpty(),
        step = state.step,
        isValid = true,
        error = null,
        source = state.source,

        onRetake = {
            Log.d(TAG, "RETAKE CLICKED step=${state.step}")
            viewModel.setStage(FlowStage.CAPTURE)
        },

        onConfirm = {
            Log.d(TAG, "CONFIRM CLICKED canFinish=${viewModel.canFinish()} photos=${state.photos.size}")

            if (viewModel.canFinish()) {
                Log.d(TAG, "CALLING saveAll()")
                viewModel.saveAll()
            } else {
                Log.d(TAG, "NOT COMPLETE -> BACK TO CAPTURE")
                viewModel.onContinue()
            }
        },

        onClose = {
            Log.d(TAG, "CLOSE CLICKED")
            viewModel.onPreviewBackClicked()
        }
    )
}