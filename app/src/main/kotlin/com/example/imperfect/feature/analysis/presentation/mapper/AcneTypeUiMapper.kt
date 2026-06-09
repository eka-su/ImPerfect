package com.example.imperfect.feature.analysis.presentation.mapper

import com.example.imperfect.core.ui.designsystem.icon.IconFilter
import com.example.imperfect.core.ui.designsystem.theme.AcneCyan
import com.example.imperfect.core.ui.designsystem.theme.AcneCyanFill
import com.example.imperfect.core.ui.designsystem.theme.AcneLime
import com.example.imperfect.core.ui.designsystem.theme.AcneLimeFill
import com.example.imperfect.core.ui.designsystem.theme.AcneOrange
import com.example.imperfect.core.ui.designsystem.theme.AcneOrangeFill
import com.example.imperfect.core.ui.designsystem.theme.AcnePink
import com.example.imperfect.core.ui.designsystem.theme.AcnePinkFill
import com.example.imperfect.core.ui.designsystem.theme.AcnePurple
import com.example.imperfect.core.ui.designsystem.theme.AcnePurpleFill
import com.example.imperfect.core.ui.designsystem.theme.AcneTeal
import com.example.imperfect.core.ui.designsystem.theme.AcneTealFill
import com.example.imperfect.feature.analysis.presentation.model.AcneTypeUi

fun acneTypeUi(classId: Int, percent: Int? = null): AcneTypeUi {

    val base = when (classId) {
        0 -> IconFilter.Blackheads
        1 -> IconFilter.DarkSpots
        2 -> IconFilter.Nodules
        3 -> IconFilter.Papules
        4 -> IconFilter.Pustules
        5 -> IconFilter.Whiteheads
        else -> IconFilter.Blackheads
    }

    return AcneTypeUi(
        name = base.contentDescription,
        percent = percent,
        color = when (base) {
            IconFilter.Blackheads -> AcneCyan
            IconFilter.DarkSpots -> AcnePurple
            IconFilter.Nodules -> AcneOrange
            IconFilter.Papules -> AcnePink
            IconFilter.Pustules -> AcneLime
            IconFilter.Whiteheads -> AcneTeal
            else -> AcneCyan
        },
        fill = when (base) {
            IconFilter.Blackheads -> AcneCyanFill
            IconFilter.DarkSpots -> AcnePurpleFill
            IconFilter.Nodules -> AcneOrangeFill
            IconFilter.Papules -> AcnePinkFill
            IconFilter.Pustules -> AcneLimeFill
            IconFilter.Whiteheads -> AcneTealFill
            else -> AcneCyanFill
        },
        iconRes = base.iconRes
    )
}