package com.example.imperfect.feature.photo.presentation.flow.component

import androidx.compose.runtime.Composable
import com.example.imperfect.core.navigation.Router
import com.example.imperfect.core.ui.kit.dialog.photosourcesheet.PhotoSourceSheet
import com.example.imperfect.feature.photo.presentation.state.FlowStage
import com.example.imperfect.feature.photo.presentation.state.Source
import com.example.imperfect.feature.photo.presentation.viewmodel.PhotoViewModel

@Composable
fun SourceScreen(
    viewModel: PhotoViewModel,
    router: Router
) {
    PhotoSourceSheet(
        onCameraClick = {
            viewModel.start(Source.CAMERA)
            viewModel.setStage(FlowStage.GUIDE)
        },
        onGalleryClick = {
            viewModel.start(Source.GALLERY)
            viewModel.setStage(FlowStage.GUIDE)
        },
        onClose = { router.back() }
    )
}