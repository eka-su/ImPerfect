package com.example.imperfect.core.ui.kit.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.theme.BottomBarGradientStart

@Composable
fun BottomBarContainer(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(76.dp) //68?
                .clip(RoundedCornerShape(32.dp))
                .background(  //градиент контейнера
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            BottomBarGradientStart,
                            MaterialTheme.colorScheme.surface
                        ),
                        startX = -120f
                    )
                )
        ) {
            content()
        }
    }
}