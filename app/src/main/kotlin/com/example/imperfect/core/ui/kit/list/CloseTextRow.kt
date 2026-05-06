package com.example.imperfect.core.ui.kit.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.imperfect.core.ui.kit.icon.clickable.CloseButton

@Composable
fun CloseTextRow(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconTextRow(
        text = text,
        icon = { CloseButton(onClick = onClick) },
        onClick = onClick,
        modifier = modifier
    )
}