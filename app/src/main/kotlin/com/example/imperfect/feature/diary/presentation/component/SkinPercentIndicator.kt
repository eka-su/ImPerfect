package com.example.imperfect.feature.diary.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary

@Composable
fun SkinPercentIndicator(
    percent: Int?
) {
    Box(
        modifier = Modifier
            .width(81.dp)
            .height(162.dp),
        contentAlignment = Alignment.Center
    ) {

        // Сам полукруг
        SkinPercentArc()

        // Процент
        SkinPercentLabel(percent)
    }
}

@Composable
private fun SkinPercentArc() {
    Canvas(modifier = Modifier.fillMaxSize()) {

        val diameter = size.height
        val radius = diameter / 2f

        drawArc(
            color = BluePrimary,
            startAngle = 270f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(-radius, 0f),
            size = Size(diameter, diameter),
            style = Stroke(
                width = 8.dp.toPx(),
                cap = StrokeCap.Round
            )
        )
    }
}

@Composable
private fun SkinPercentLabel(percent: Int?) {
    Text(
        text = "${percent ?: 0}%",
        color = BluePrimary,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.offset(x = -16.dp)
    )
}