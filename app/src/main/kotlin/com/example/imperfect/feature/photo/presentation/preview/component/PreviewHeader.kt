package com.example.imperfect.feature.photo.presentation.preview.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.imperfect.R
import com.example.imperfect.core.ui.kit.list.CloseTextRow

@Composable
fun PreviewHeader(
    step: Int,
    onClose: () -> Unit
) {
    CloseTextRow(
        text = stringResource(R.string.preview_title, step),
        onClick = onClose
    )
}