package com.example.imperfect.feature.analysis.presentation.utils

import androidx.compose.ui.graphics.Color
import com.example.imperfect.core.ui.designsystem.theme.AcneCyan
import com.example.imperfect.core.ui.designsystem.theme.AcneLime
import com.example.imperfect.core.ui.designsystem.theme.AcneOrange
import com.example.imperfect.core.ui.designsystem.theme.AcnePink
import com.example.imperfect.core.ui.designsystem.theme.AcnePurple
import com.example.imperfect.core.ui.designsystem.theme.AcneTeal

fun acneColor(classId: Int): Color {
    return when (classId) {
        0 -> AcneCyan
        1 -> AcnePurple
        2 -> AcneOrange
        3 -> AcnePink
        4 -> AcneLime
        5 -> AcneTeal
        else -> AcneCyan
    }
}