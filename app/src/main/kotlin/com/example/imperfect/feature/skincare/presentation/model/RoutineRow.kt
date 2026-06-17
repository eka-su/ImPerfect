package com.example.imperfect.feature.skincare.presentation.model

import com.example.imperfect.feature.skincare.domain.model.CareProduct
import com.example.imperfect.feature.skincare.domain.model.CareTime

data class RoutineRow(
    val product: CareProduct,
    val time: CareTime
)