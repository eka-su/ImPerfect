package com.example.imperfect.core.ui.designsystem.icon

import androidx.compose.ui.graphics.Color
import com.example.imperfect.core.ui.designsystem.theme.BlueLightBackground
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.designsystem.theme.IconGray

data class IconStyle(
    val background: Color,
    val tint: Color
)

object IconStyles {

    val Default = IconStyle(
        background = BlueLightBackground,
        tint = IconGray
    )

    val Primary = IconStyle(
        background = BlueLightBackground,
        tint = BluePrimary
    )
}