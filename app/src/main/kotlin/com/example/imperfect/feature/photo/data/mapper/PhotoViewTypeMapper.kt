package com.example.imperfect.feature.photo.data.mapper

import com.example.imperfect.core.database.entity.PhotoViewTypeEntity
import com.example.imperfect.feature.photo.domain.model.PhotoViewType

fun PhotoViewTypeEntity.toDomain(): PhotoViewType {
    return PhotoViewType(
        id = id,
        code = code,
        name = name
    )
}