package com.example.imperfect.feature.analysis.presentation.model

import androidx.compose.ui.graphics.Color

data class AcneTypeUi(
    val name: String,
    val percent: Int? = null,
    val color: Color,
    val fill: Color,
    val iconRes: Int
)