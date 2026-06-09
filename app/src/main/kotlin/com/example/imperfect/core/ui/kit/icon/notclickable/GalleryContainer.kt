package com.example.imperfect.core.ui.kit.icon.notclickable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.imperfect.core.ui.designsystem.icon.IconAction
import com.example.imperfect.core.ui.designsystem.icon.IconSize
import com.example.imperfect.core.ui.designsystem.icon.IconStyle
import com.example.imperfect.core.ui.designsystem.icon.IconStyles
import com.example.imperfect.core.ui.kit.icon.primitives.IconContainer

@Composable
fun GalleryContainer(
    modifier: Modifier = Modifier,
    size: IconSize = IconSize.SMALL,
    style: IconStyle = IconStyles.Primary
) {
    IconContainer(
        action = IconAction.Gallery,
        modifier = modifier,
        size = size,
        style = style
    )
}