package com.example.imperfect.core.ui.kit.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.theme.BlueLightBackground
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary

@Composable
fun FlexibleChip(
    modifier: Modifier = Modifier,

    // дизайн по умолчанию
    backgroundColor: Color = BlueLightBackground,
    cornerRadius: Dp = 30.dp,

    // текст стиль по умолчанию
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    textColor: Color = TextSecondary,

    // content
    content: @Composable RowScope.() -> Unit
) {

    Row(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(cornerRadius)
            )
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        content = content
    )
}