package com.example.imperfect.feature.analysis.presentation.utils

import com.example.imperfect.feature.photo.domain.model.Photo

fun getScanTime(photos: List<Photo>): String {
    val frontPhoto = photos.firstOrNull { it.viewTypeId == 1 }
    val baseTime = frontPhoto?.createdAt ?: photos.firstOrNull()?.createdAt ?: return ""
    return formatScanDate(baseTime)
}