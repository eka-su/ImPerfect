package com.example.imperfect.feature.photo.presentation.flow.component

import androidx.compose.runtime.Composable
import com.example.imperfect.core.navigation.Router
import com.example.imperfect.feature.photo.presentation.guide.screen.PhotoGuideScreen
import com.example.imperfect.feature.photo.presentation.state.FlowStage
import com.example.imperfect.feature.photo.presentation.viewmodel.PhotoViewModel

@Composable
fun GuideScreen(
    viewModel: PhotoViewModel,
    router: Router
) {
    PhotoGuideScreen(
        onStartClick = {
            viewModel.setStage(FlowStage.CAPTURE)
        },
        onBack = { router.back() }
    )
}