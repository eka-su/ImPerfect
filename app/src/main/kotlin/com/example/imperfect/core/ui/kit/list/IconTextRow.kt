package com.example.imperfect.core.ui.kit.list

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.theme.ImPerfectTheme
import com.example.imperfect.core.ui.designsystem.theme.TextTitle
import com.example.imperfect.core.ui.kit.icon.clickable.BackButton

@Composable
fun IconTextRow(
    text: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    fullRowClickable: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge,
    textColor: Color = TextTitle,
    iconSpacing: Dp = 20.dp,
    verticalPadding: Dp = 0.dp
) {

    val rowModifier = if (fullRowClickable) {
        modifier.clickable { onClick() }
    } else {
        modifier
    }

    Row(
        modifier = rowModifier.padding(vertical = verticalPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (fullRowClickable) {
            icon()
        } else {
            Box(modifier = Modifier.clickable { onClick() }) {
                icon()
            }
        }

        Spacer(modifier = Modifier.width(iconSpacing))

        Text(
            text = text,
            color = textColor,
            style = textStyle,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IconTextRowPreview() {
    ImPerfectTheme {
        IconTextRow(
            text = "Назад",
            icon = {
                BackButton(onClick = {})
            },
            onClick = {}
        )
    }
}