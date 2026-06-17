package com.example.imperfect.feature.skincare.data.mapper

import com.example.imperfect.core.database.entity.CareTimeSlotEntity
import com.example.imperfect.core.database.entity.SkincareCategoryEntity
import com.example.imperfect.feature.skincare.data.local.relation.ProductWithCategory
import com.example.imperfect.feature.skincare.data.local.relation.RoutineWithDetails
import com.example.imperfect.feature.skincare.domain.model.CareProduct
import com.example.imperfect.feature.skincare.domain.model.CareRoutine
import com.example.imperfect.feature.skincare.domain.model.CareTime
import com.example.imperfect.feature.skincare.domain.model.ProductCategory

fun SkincareCategoryEntity.toDomain() =
    ProductCategory(
        id = id,
        name = name,
        code = code
    )

fun CareTimeSlotEntity.toDomain() =
    CareTime(
        id = id,
        code = code,
        name = name
    )

fun ProductWithCategory.toDomain() =
    CareProduct(
        id = product.id,
        name = product.name,
        brand = product.brand,
        category = category?.toDomain(),
        description = product.description,
        imagePath = product.imagePath,
        isFavorite = product.isFavorite,
        createdAt = product.createdAt
    )

fun RoutineWithDetails.toDomain() =
    CareRoutine(
        id = routine.id,
        diaryId = routine.diaryId,
        product = CareProduct(
            id = product.id,
            name = product.name,
            brand = product.brand,
            category = null,
            description = product.description,
            imagePath = product.imagePath,
            isFavorite = product.isFavorite,
            createdAt = product.createdAt
        ),
        times = times.map { it.slot.toDomain() },
        isUsed = routine.isUsed
    )