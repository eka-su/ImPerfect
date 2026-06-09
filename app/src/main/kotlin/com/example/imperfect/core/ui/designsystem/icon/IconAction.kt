package com.example.imperfect.core.ui.designsystem.icon

import com.example.imperfect.R

sealed class IconAction(
    val iconRes: Int,
    val contentDescription: String
) {
    data object Back : IconAction(
        R.drawable.ic_back,
        "Back"
    )

    data object Close : IconAction(
        R.drawable.ic_close,
        "Close"
    )

    data object Camera : IconAction(
        R.drawable.ic_camera,
        "Camera"
    )

    data object Gallery : IconAction(
        R.drawable.ic_gallery,
        "Gallery"
    )

    data object EyeOpen : IconAction(
        R.drawable.ic_eye_open,
        "Show"
    )

    data object EyeClose : IconAction(
        R.drawable.ic_eye_close,
        "Hide"
    )

    data object Edit : IconAction(
        R.drawable.ic_edit,
        "Edit"
    )

    data object All: IconAction(
        R.drawable.ic_all,
        "All"
    )

    data object Select: IconAction(
        R.drawable.ic_select,
        "Select"
    )

    data object Location: IconAction(
        R.drawable.ic_location,
        "Location"
    )

    data object Next: IconAction(
        R.drawable.ic_next,
        "Next"
    )

    data object Folder : IconAction(
        R.drawable.ic_folder,
        "Folder"
    )

    data object Calendar : IconAction(
        R.drawable.ic_calendar,
        "Calendar"
    )

    data object Skincare : IconAction(
        R.drawable.ic_skincare,
        "Уход за кожей"
    )

    data object Food : IconAction(
        R.drawable.ic_food,
        "Питание"
    )

    data object Skin : IconAction(
        R.drawable.ic_skin_sensation,
        "Ощущение кожи"
    )

    data object Health : IconAction(
        R.drawable.ic_health,
        "Здоровье и чувства"
    )

    data object Lifestyle : IconAction(
        R.drawable.ic_lifestyle,
        "Образ жизни"
    )
}