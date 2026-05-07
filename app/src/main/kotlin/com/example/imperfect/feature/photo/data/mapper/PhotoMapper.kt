package com.example.imperfect.feature.photo.data.mapper

import com.example.imperfect.core.database.entity.DiaryPhotoEntity
import com.example.imperfect.feature.photo.domain.model.Photo

fun DiaryPhotoEntity.toDomain(): Photo =
    Photo(
        id = id,
        diaryId = diaryId,
        filePath = filePath,
        viewTypeId = viewTypeId,
        createdAt = createdAt
    )

fun Photo.toEntity(): DiaryPhotoEntity =
    DiaryPhotoEntity(
        id = id,
        diaryId = diaryId,
        filePath = filePath,
        viewTypeId = viewTypeId,
        createdAt = createdAt
    )