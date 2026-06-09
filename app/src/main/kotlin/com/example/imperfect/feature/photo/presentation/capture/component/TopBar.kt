package com.example.imperfect.feature.photo.presentation.capture.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.imperfect.R
import com.example.imperfect.core.ui.kit.icon.clickable.SolidBackButton

@Composable
fun TopBar(
    step: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {

        SolidBackButton(
            onClick = onBack,
            modifier = Modifier.align(Alignment.CenterStart)
        )

        Text(
            text = stringResource(R.string.capture_step, step+1),
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}