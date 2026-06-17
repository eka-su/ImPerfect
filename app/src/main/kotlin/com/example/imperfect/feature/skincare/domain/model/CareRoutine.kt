package com.example.imperfect.feature.skincare.domain.model

data class CareRoutine(
    val id: Int,
    val diaryId: Int,
    val product: CareProduct,
    val times: List<CareTime>,
    val isUsed: Boolean
)