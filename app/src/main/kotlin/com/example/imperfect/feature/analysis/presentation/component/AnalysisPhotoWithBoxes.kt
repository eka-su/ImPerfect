package com.example.imperfect.feature.analysis.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import com.example.imperfect.feature.analysis.domain.model.Detection
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.imperfect.feature.analysis.presentation.utils.acneColor

@Composable
fun AnalysisPhotoWithBoxes(
    photoPath: String,
    detections: List<Detection>,
    showDetections: Boolean
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = photoPath,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        if (showDetections) {
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                val canvasWidth = size.width
                val canvasHeight = size.height

                detections.forEach { detection ->

                    val boxWidth =
                        (detection.width * canvasWidth).toFloat()

                    val boxHeight =
                        (detection.height * canvasHeight).toFloat()

                    val centerX =
                        (detection.x * canvasWidth).toFloat()

                    val centerY =
                        (detection.y * canvasHeight).toFloat()

                    val left = centerX - boxWidth / 2f
                    val top = centerY - boxHeight / 2f

                    drawRect(
                        color = acneColor(detection.classId),
                        topLeft = Offset(left, top),
                        size = Size(boxWidth, boxHeight),
                        style = Stroke(width = 2.dp.toPx())
                    )
                }
            }
        }
    }
}

