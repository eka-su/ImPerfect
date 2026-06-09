package com.example.imperfect.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imperfect.core.database.entity.AnalysisContextEntity

@Dao
interface AnalysisContextDao {

    // INSERT / UPDATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: AnalysisContextEntity): Long

    // GET
    @Query("""
        SELECT * FROM analysis_context
        WHERE multi_analysis_id = :multiId
        LIMIT 1
    """)
    suspend fun getByMultiId(multiId: Int): AnalysisContextEntity?

    // DELETE
    @Query("""
        DELETE FROM analysis_context
        WHERE multi_analysis_id = :multiId
    """)
    suspend fun deleteByMultiId(multiId: Int)
}