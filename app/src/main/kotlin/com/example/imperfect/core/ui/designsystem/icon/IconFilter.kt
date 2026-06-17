package com.example.imperfect.core.ui.designsystem.icon

import com.example.imperfect.R

sealed class IconFilter(
    override val iconRes: Int,
    override val contentDescription: String
) : AppIcon {

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

    //уход категории
    data object Lotion : IconFilter(
        R.drawable.ic_lotion,
        "Лосьон"
    )

    data object Cream : IconFilter(
        R.drawable.ic_cream,
        "Крем"
    )

    data object Balm : IconFilter(
        R.drawable.ic_balm,
        "Бальзам"
    )

    data object Mask : IconFilter(
        R.drawable.ic_mask,
        "Маска"
    )

    data object Toner : IconFilter(
        R.drawable.ic_toner,
        "Тоник"
    )

    data object Gel : IconFilter(
        R.drawable.ic_gel,
        "Гель"
    )

    data object Emulsion : IconFilter(
        R.drawable.ic_emulsion,
        "Эмульсия"
    )

    data object Serum : IconFilter(
        R.drawable.ic_serum,
        "Сыворотка"
    )

    data object OtherCare : IconFilter(
        R.drawable.ic_other_care,
        "Другой уход"
    )

    // Утро ночь все
    data object Morning : IconFilter(
        R.drawable.ic_morning,
        "Утро"
    )

    data object Evening : IconFilter(
        R.drawable.ic_evening,
        "Вечер"
    )

    data object Alltime : IconFilter(
        R.drawable.ic_all_time,
        "Утром и вечер"
    )

}