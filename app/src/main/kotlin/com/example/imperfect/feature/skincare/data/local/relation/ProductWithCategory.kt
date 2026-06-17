package com.example.imperfect.feature.skincare.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.imperfect.core.database.entity.SkincareCategoryEntity
import com.example.imperfect.core.database.entity.SkincareProductEntity

data class ProductWithCategory(

    @Embedded
    val product: SkincareProductEntity,

    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val category: SkincareCategoryEntity?
)