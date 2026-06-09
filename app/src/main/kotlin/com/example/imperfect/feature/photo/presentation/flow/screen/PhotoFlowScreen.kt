package com.example.imperfect.feature.photo.presentation.flow.screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.imperfect.core.navigation.Router
import com.example.imperfect.core.ui.kit.dialog.ExitConfirmDialog
import com.example.imperfect.feature.photo.presentation.event.PhotoFlowEvent
import com.example.imperfect.feature.photo.presentation.flow.component.PhotoFlowContent
import com.example.imperfect.feature.photo.presentation.viewmodel.PhotoViewModel

@Composable
fun PhotoFlowScreen(
    viewModel: PhotoViewModel,
    router: Router
) {
    val state by viewModel.state.collectAsState()
    val event by viewModel.event.collectAsState()

    Log.d("PHOTO_TRACE", "====================")
    Log.d("PHOTO_TRACE", "PhotoFlowScreen COMPOSE")
    Log.d("PHOTO_TRACE", "vm=${viewModel.hashCode()}")
    Log.d("PHOTO_TRACE", "stage=${state.stage}")
    Log.d("PHOTO_TRACE", "source=${state.source}")
    Log.d("PHOTO_TRACE", "step=${state.step}")
    Log.d("PHOTO_TRACE", "photos=${state.photos.size}")
    Log.d("PHOTO_TRACE", "event=$event")

    LaunchedEffect(event) {
        Log.d("PHOTO_TRACE", "LaunchedEffect event=$event")

        when (val current = event) {
            is PhotoFlowEvent.OpenAnalysis -> {
                Log.d("PHOTO_TRACE", "NAVIGATE -> Analysis ${current.photoIds}")
                router.openAnalysis(
                    photoIds = current.photoIds,
                    diaryId = current.diaryId
                )
            }

            PhotoFlowEvent.Exit -> {
                Log.d("PHOTO_TRACE", "NAVIGATE -> Exit")
                router.openHomeAndClearPhotoFlow()
            }

            null -> {
                Log.d("PHOTO_TRACE", "event = null")
            }
        }
    }

    if (state.showExitDialog) {
        Log.d("PHOTO_TRACE", "SHOW EXIT DIALOG")

        ExitConfirmDialog(
            onConfirm = {
                Log.d("PHOTO_TRACE", "EXIT CONFIRMED")
                viewModel.confirmExit()
                router.back()
            },
            onDismiss = {
                Log.d("PHOTO_TRACE", "EXIT DISMISSED")
                viewModel.dismissExitDialog()
            }
        )
    }

    Log.d("PHOTO_TRACE", "CALL PhotoFlowContent")
    PhotoFlowContent(state, viewModel, router)
}