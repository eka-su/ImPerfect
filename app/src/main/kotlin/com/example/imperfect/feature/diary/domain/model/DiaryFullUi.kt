package com.example.imperfect.feature.diary.domain.model

import com.example.imperfect.feature.skincare.data.local.relation.RoutineWithDetails

data class DiaryFullUi(
    val id: Int,
    val userId: Int,
    val date: String,
    val photos: List<DiaryPhotoPreview>,
    val analysisId: Int?,
    val analysisPercent: Int?,
    val skincare: List<RoutineWithDetails> = emptyList()
)
