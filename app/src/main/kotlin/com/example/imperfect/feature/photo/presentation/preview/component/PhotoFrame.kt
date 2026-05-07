package com.example.imperfect.feature.photo.presentation.preview.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.imperfect.R
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.kit.container.SheetContainer

@Composable
fun PhotoFrame(
    photoPath: String,
    isValid: Boolean,
    error: String?
) {
    SheetContainer {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 4f)
                .padding(16.dp)
        ) {

            CornerDecorations()

            Column(modifier = Modifier.padding(12.dp)) {

                PreviewImage(photoPath)

                if (!isValid && error != null) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = error,
                        color = Color.Red
                    )
                }
            }
        }
    }
}

@Composable
fun BoxScope.CornerDecorations() {

    @Composable
    fun Corner(modifier: Modifier = Modifier) {
        Icon(
            painter = painterResource(R.drawable.ic_corner),
            contentDescription = null,
            tint = BluePrimary,
            modifier = modifier
        )
    }

    Corner(Modifier.align(Alignment.TopStart))

    Corner(
        Modifier
            .align(Alignment.TopEnd)
            .graphicsLayer(scaleX = -1f)
    )

    Corner(
        Modifier
            .align(Alignment.BottomStart)
            .graphicsLayer(scaleY = -1f)
    )

    Corner(
        Modifier
            .align(Alignment.BottomEnd)
            .graphicsLayer(scaleX = -1f, scaleY = -1f)
    )
}