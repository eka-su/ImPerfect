package com.example.imperfect.core.ui.kit.icon.clickable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.imperfect.core.ui.kit.icon.primitives.IconActionButton
import com.example.imperfect.core.ui.designsystem.icon.IconAction
import com.example.imperfect.core.ui.designsystem.icon.IconSize
import com.example.imperfect.core.ui.designsystem.icon.IconStyles

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconActionButton(
        action = IconAction.Back,
        onClick = onClick,
        modifier = modifier,
        size = IconSize.SMALL,
        style = IconStyles.Default
    )
}