package com.example.imperfect.feature.photo.presentation.guide.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.kit.icon.clickable.BackButton
import com.example.imperfect.feature.photo.presentation.guide.component.PhotoGuideContent

@Composable
fun PhotoGuideScreen(
    onStartClick: () -> Unit,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        BackButton(
            onClick = onBack,
            modifier = Modifier.align(Alignment.TopStart)
        )

        PhotoGuideContent(
            onStartClick = onStartClick,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}