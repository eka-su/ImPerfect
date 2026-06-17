package com.example.imperfect.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "skincare_products",
    foreignKeys = [
        ForeignKey(
            entity = SkincareCategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index("categoryId")
    ]
)
data class SkincareProductEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    val brand: String? = null,

    val categoryId: Int? = null,

    val description: String? = null,

    val imagePath: String? = null,

    val createdAt: String,

    val isFavorite: Boolean = false
)