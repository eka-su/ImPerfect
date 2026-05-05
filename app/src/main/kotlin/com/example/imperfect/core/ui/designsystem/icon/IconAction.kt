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
}