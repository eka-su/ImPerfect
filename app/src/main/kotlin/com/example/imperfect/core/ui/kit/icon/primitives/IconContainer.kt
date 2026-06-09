package com.example.imperfect.core.ui.kit.icon.primitives

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.icon.IconAction
import com.example.imperfect.core.ui.designsystem.icon.IconSize
import com.example.imperfect.core.ui.designsystem.icon.IconStyle
import com.example.imperfect.core.ui.designsystem.icon.IconStyles

@Composable
fun IconContainer(
    action: IconAction,
    modifier: Modifier = Modifier,
    size: IconSize = IconSize.SMALL,
    style: IconStyle = IconStyles.Default
) {
    Box(
        modifier = modifier
            .size(size.container)
            .background(
                color = style.background,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = action.iconRes),
            contentDescription = action.contentDescription,
            tint = style.tint,
            modifier = Modifier.size(size.icon)
        )
    }
}