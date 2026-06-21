package com.example.imperfect.feature.feeling.presentation.component

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.imperfect.core.ui.designsystem.icon.IconFilter
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary

@Composable
fun IconFilterView(
    icon: IconFilter,
    tint: Color = TextSecondary
) {
    Icon(
        painter = painterResource(id = icon.iconRes),
        contentDescription = icon.contentDescription,
        tint = tint
    )
}