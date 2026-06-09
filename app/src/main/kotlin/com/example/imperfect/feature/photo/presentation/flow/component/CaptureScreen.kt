package com.example.imperfect.feature.photo.presentation.flow.component

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

    LaunchedEffect(state.source, state.stage) {
        Log.d("PHOTO_FLOW", "CaptureScreen state = $state")
    }

    Log.d("PHOTO_FLOW", "CaptureScreen RECOMPOSE source=${state.source}, stage=${state.stage}")

    Log.d("PHOTO_FLOW", "BRANCH CHECK source = ${state.source}")

    when (state.source) {
        Source.CAMERA -> {
            Log.d("PHOTO_FLOW", "OPEN CAMERA SCREEN")
            PhotoCaptureScreen(
                step = state.step,
                order = state.photoOrder,
                onTakePhoto = {
                    viewModel.addPhoto(it)
                    viewModel.setStage(FlowStage.PREVIEW)
                },
                onBack = {
                    viewModel.setStage(FlowStage.GUIDE)
                }
            )
        }

        Source.GALLERY -> {

            Log.d("PHOTO_FLOW", "OPEN GALLERY SCREEN")
            GalleryPickerScreen(
                onSelect = {
                    viewModel.addPhoto(it)
                    viewModel.setStage(FlowStage.PREVIEW)
                }
            )
        }

        Source.NONE -> {
            // пустое состояние
        }

        else -> {
            Log.d("PHOTO_FLOW", "UNKNOWN SOURCE = ${state.source}")
        }

    }


}
