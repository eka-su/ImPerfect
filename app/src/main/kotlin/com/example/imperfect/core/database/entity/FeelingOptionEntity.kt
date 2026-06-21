package com.example.imperfect.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "feeling_options",
    foreignKeys = [
        ForeignKey(
            entity = FeelingScreenEntity::class,
            parentColumns = ["id"],
            childColumns = ["screen_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("screen_id")]
)
data class FeelingOptionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "screen_id")
    val screenId: Int,

    val name: String
)