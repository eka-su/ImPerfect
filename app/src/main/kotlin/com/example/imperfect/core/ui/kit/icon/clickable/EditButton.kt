package com.example.imperfect.core.ui.kit.icon.clickable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.imperfect.core.ui.designsystem.icon.IconAction
import com.example.imperfect.core.ui.designsystem.icon.IconSize
import com.example.imperfect.core.ui.designsystem.icon.IconStyles
import com.example.imperfect.core.ui.kit.icon.primitives.IconActionButton

@Composable
fun EditButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconActionButton(
        action = IconAction.Edit,
        onClick = onClick,
        modifier = modifier,
        size = IconSize.SMALL,
        style = IconStyles.Default
    )
}