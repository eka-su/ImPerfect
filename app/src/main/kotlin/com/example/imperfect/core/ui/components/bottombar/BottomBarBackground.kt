package com.example.imperfect.core.ui.components.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.theme.Background
import com.example.imperfect.core.ui.theme.BlueShadowLight

@Composable
fun BottomBarBackground() { //голубая тень вверх
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .offset(y = (-40).dp) //поднимаем вверх
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Background,
                        BlueShadowLight,
                        Color.Transparent
                    )
                )
            )
    )
}