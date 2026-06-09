package com.example.imperfect.feature.photo.presentation.capture.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.imperfect.R
import com.example.imperfect.feature.photo.domain.model.PhotoViewType

@Composable
fun StepOverlay(
    step: Int,
    order: List<PhotoViewType>
) {

    val currentType = order.getOrNull(step)
        ?: order.firstOrNull()
        ?: return

    val instruction = when (currentType.code) {
        "FRONT" -> stringResource(R.string.capture_instruction_1)
        "LEFT" -> stringResource(R.string.capture_instruction_2)
        "RIGHT" -> stringResource(R.string.capture_instruction_3)
        else -> ""
    }

    val maskIcon = when (currentType.code) {
        "FRONT" -> R.drawable.ic_front
        "LEFT" -> R.drawable.ic_left
        "RIGHT" -> R.drawable.ic_right
        else -> R.drawable.ic_front
    }

    Box(Modifier.fillMaxSize()) {

        Icon(
            painter = painterResource(maskIcon),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .size(400.dp)
        )

        Text(
            text = "$instruction (${step + 1}/${order.size})",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp)
        )
    }
}