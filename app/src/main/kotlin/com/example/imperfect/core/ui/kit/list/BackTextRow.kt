package com.example.imperfect.core.ui.kit.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.imperfect.core.ui.designsystem.theme.ImPerfectTheme
import com.example.imperfect.core.ui.kit.icon.clickable.BackButton

@Composable
fun BackTextRow(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconTextRow(
        text = text,
        icon = { BackButton(onClick = onClick) },
        onClick = onClick,
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun BackTextRowPreview() {
    ImPerfectTheme {
        BackTextRow(
            text = "Назад",
            onClick = {}
        )
    }
}