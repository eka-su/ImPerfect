package com.example.imperfect.feature.diary.domain.model

data class DiaryFullUi(
    val id: Int,
    val userId: Int,
    val date: String,
    val photos: List<DiaryPhotoPreview>,
    val analysisId: Int?,
    val analysisPercent: Int?
)
