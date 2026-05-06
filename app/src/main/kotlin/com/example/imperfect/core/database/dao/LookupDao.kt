package com.example.imperfect.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.imperfect.core.database.entity.PhotoAnalysisStatusEntity
import com.example.imperfect.core.database.entity.PhotoViewTypeEntity

@Dao
interface LookupDao {

    @Query("SELECT * FROM photo_view_type")
    suspend fun getViewTypes(): List<PhotoViewTypeEntity>

    @Query("SELECT * FROM photo_analysis_status")
    suspend fun getStatuses(): List<PhotoAnalysisStatusEntity>
}