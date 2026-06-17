package com.example.imperfect.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imperfect.core.database.entity.SkinDiaryDayEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {

    // INSERT / UPDATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(day: SkinDiaryDayEntity): Long

    // READ (SINGLE DAY)
    @Query("""
        SELECT * FROM skin_diary_day
        WHERE user_id = :userId AND date = :date
        LIMIT 1
    """)
    suspend fun getByDate(userId: Int, date: String): SkinDiaryDayEntity?

    @Query("""
        SELECT * FROM skin_diary_day
        WHERE user_id = :userId AND date = :date
        LIMIT 1
    """)
    fun observeByDate(userId: Int, date: String): Flow<SkinDiaryDayEntity>

    @Query("SELECT * FROM skin_diary_day WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): SkinDiaryDayEntity?

    // READ (LISTS)
    @Query("""
        SELECT *
        FROM skin_diary_day
    """)
    suspend fun getAllDays(): List<SkinDiaryDayEntity>

    @Query("""
    SELECT DISTINCT d.*
    FROM skin_diary_day d

    LEFT JOIN diary_photo p
        ON p.diary_id = d.id

    LEFT JOIN multi_image_analysis a
        ON a.diary_id = d.id

    LEFT JOIN care_routine_items c
        ON c.diaryId = d.id

    WHERE
        p.id IS NOT NULL
        OR a.id IS NOT NULL
        OR c.id IS NOT NULL
    """)
    suspend fun getFilledDays(): List<SkinDiaryDayEntity>

    @Query("""
    SELECT DISTINCT d.*
    FROM skin_diary_day d
   LEFT JOIN diary_photo p ON p.diary_id = d.id
   LEFT JOIN multi_image_analysis a ON a.diary_id = d.id
   LEFT JOIN care_routine_items c ON c.diaryId = d.id
   WHERE p.id IS NOT NULL
      OR a.id IS NOT NULL
      OR c.id IS NOT NULL
   """)
    fun observeFilledDays(): Flow<List<SkinDiaryDayEntity>>

}