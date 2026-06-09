package com.example.imperfect.feature.photo.presentation.capture.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.imperfect.R
import com.example.imperfect.core.ui.designsystem.theme.Background
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.kit.button.PrimaryButton

@Composable
fun CaptureButton(
    isTakingPhoto: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    PrimaryButton(
        onClick = {
            if (!isTakingPhoto) onClick()
        },
        modifier = modifier,
        containerColor = BluePrimary,
        contentColor = Background
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_take_photo),
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )
    }
}