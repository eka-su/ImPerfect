package com.example.imperfect.feature.photo.domain.model

data class Photo(
    val id: Int = 0,
    val diaryId: Int,
    val filePath: String,
    val viewTypeId: Int,
    val createdAt: String
)