package com.example.imperfect.core.ui.kit.dialog.photosourcesheet

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary
import com.example.imperfect.core.ui.kit.list.IconTextRow

@Composable
fun PhotoSheetRow(
    text: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconTextRow(
        text = text,
        icon = icon,
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodySmall,
        textColor = TextSecondary,
        iconSpacing = 12.dp,
        verticalPadding = 8.dp
    )
}