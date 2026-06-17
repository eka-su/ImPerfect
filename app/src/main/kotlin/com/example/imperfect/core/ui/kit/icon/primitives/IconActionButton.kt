package com.example.imperfect.core.ui.kit.icon.primitives

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.imperfect.core.ui.designsystem.icon.AppIcon
import com.example.imperfect.core.ui.designsystem.icon.IconSize
import com.example.imperfect.core.ui.designsystem.icon.IconStyle
import com.example.imperfect.core.ui.designsystem.icon.IconStyles

@Composable
fun IconActionButton(
    action: AppIcon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: IconSize = IconSize.SMALL,
    style: IconStyle = IconStyles.Default
) {
    IconContainer(
        action = action,
        size = size,
        style = style,
        modifier = modifier.clickable(onClick = onClick)
    )
}

