package com.example.imperfect.feature.photo.presentation.flow.component

import androidx.compose.runtime.Composable
import com.example.imperfect.core.navigation.Router
import com.example.imperfect.feature.photo.presentation.state.FlowStage
import com.example.imperfect.feature.photo.presentation.state.PhotoFlowState
import com.example.imperfect.feature.photo.presentation.viewmodel.PhotoViewModel

@Composable
fun PhotoFlowContent(
    state: PhotoFlowState,
    viewModel: PhotoViewModel,
    router: Router
) {
    when (state.stage) {
        FlowStage.SOURCE -> SourceScreen(viewModel, router)
        FlowStage.GUIDE -> GuideScreen(viewModel, router)
        FlowStage.CAPTURE -> CaptureScreen(state, viewModel)
        FlowStage.PREVIEW -> PreviewScreen(state, viewModel)
    }
}
