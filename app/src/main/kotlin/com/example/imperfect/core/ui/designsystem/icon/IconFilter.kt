package com.example.imperfect.core.ui.designsystem.icon

import com.example.imperfect.R

sealed class IconFilter(
    val iconRes: Int,
    val contentDescription: String
) {

    data object AllZones : IconFilter(
        R.drawable.ic_all_face,
        "All"
    )

    data object Front2 : IconFilter(
        R.drawable.ic_front2,
        "Front"
    )

    data object Left2 : IconFilter(
        R.drawable.ic_left2,
        "Left"
    )

    data object Right2 : IconFilter(
        R.drawable.ic_right2,
        "Right"
    )

    data object Blackheads : IconFilter(
        R.drawable.ic_blackheads,
        "Blackheads"
    )

    data object DarkSpots : IconFilter(
        R.drawable.ic_dark_spots,
        "Dark Spots"
    )

    data object Nodules : IconFilter(
        R.drawable.ic_nodules,
        "Nodules"
    )

    data object Papules : IconFilter(
        R.drawable.ic_papules,
        "Papules"
    )

    data object Pustules : IconFilter(
        R.drawable.ic_pustules,
        "Pustules"
    )

    data object Whiteheads : IconFilter(
        R.drawable.ic_whiteheads,
        "Whiteheads"
    )

    data object QuestionMan  : IconFilter(
        R.drawable.ic_question_man,
        "Question Man"
    )

}