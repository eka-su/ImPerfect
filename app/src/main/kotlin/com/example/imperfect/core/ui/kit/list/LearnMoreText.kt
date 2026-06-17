package com.example.imperfect.core.ui.kit.list

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.icon.IconAction
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary

@Composable
fun LearnMoreText(
    text: String = "Раскрыть",
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    fullRowClickable: Boolean = true
) {
    IconTextRow(
        text = text,
        onClick = onClick,
        modifier = modifier,
        fullRowClickable = fullRowClickable,
        iconAtEnd = true,
        textStyle = MaterialTheme.typography.labelSmall,
        textColor = BluePrimary,
        iconSpacing = 4.dp,
        icon = {
            Icon(
                painter = painterResource(IconAction.More.iconRes),
                contentDescription = text,
                tint = BluePrimary,
                modifier = Modifier.size(14.dp)
            )
        }
    )
}