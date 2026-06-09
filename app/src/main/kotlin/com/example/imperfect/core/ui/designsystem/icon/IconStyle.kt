package com.example.imperfect.core.ui.designsystem.icon

import androidx.compose.ui.graphics.Color
import com.example.imperfect.core.ui.designsystem.theme.Background
import com.example.imperfect.core.ui.designsystem.theme.BlueLightBackground
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.designsystem.theme.IconGray
import com.example.imperfect.core.ui.designsystem.theme.InputBackground

data class IconStyle(
    val background: Color,
    val tint: Color
)

object IconStyles {

    val Default = IconStyle(  //серый на белом прозрачном
        background = BlueLightBackground,
        tint = IconGray
    )

    val Primary = IconStyle(  //голубой на белом прозрачном
        background = BlueLightBackground,
        tint = BluePrimary
    )

    val Input = IconStyle(  //серый на белом непрозрачном
        background = InputBackground,
        tint = IconGray
    )

    val InputPrimary = IconStyle(  //голубой на белом непрозраччном
        background = InputBackground,
        tint = BluePrimary
    )

    val Filled = IconStyle( //белый на голубом
        background = BluePrimary,
        tint = Background
    )
}