package com.example.imperfect.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo_analysis_status")
data class PhotoAnalysisStatusEntity(

    @PrimaryKey
    val id: Int,

    val code: String,
    val name: String
)