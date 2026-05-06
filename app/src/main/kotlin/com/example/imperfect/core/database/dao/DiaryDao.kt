package com.example.imperfect.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imperfect.core.database.entity.SkinDiaryDayEntity

@Dao
interface DiaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(day: SkinDiaryDayEntity): Long

    @Query("""
        SELECT * FROM skin_diary_day 
        WHERE user_id = :userId AND date = :date 
        LIMIT 1
    """)
    suspend fun getByDate(userId: Int, date: String): SkinDiaryDayEntity?
}