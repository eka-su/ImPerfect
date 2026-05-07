package com.example.imperfect.feature.photo.presentation.flow.screen

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

    LaunchedEffect(event) {
        when (event) {
            PhotoFlowEvent.Exit -> router.openHomeAndClearPhotoFlow()
            null -> Unit
        }
    }

    if (state.showExitDialog) {
        ExitConfirmDialog(
            onConfirm = {
                viewModel.confirmExit()
                router.back()
            },
            onDismiss = viewModel::dismissExitDialog
        )
    }

    PhotoFlowContent(state, viewModel, router)
}





