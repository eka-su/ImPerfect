package com.example.imperfect.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.imperfect.core.database.entity.DiaryPhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    // INSERT
    @Insert
    suspend fun insertPhoto(photo: DiaryPhotoEntity): Long

    // GET
    @Query("SELECT * FROM diary_photo WHERE diary_id = :diaryId")
    suspend fun getPhotos(diaryId: Int): List<DiaryPhotoEntity>

    @Query("""
    SELECT * FROM diary_photo
    WHERE id IN (:ids)
""")
    suspend fun getByIds(
        ids: List<Int>
    ): List<DiaryPhotoEntity>

    @Query("""
    SELECT * FROM diary_photo
    WHERE diary_id = :diaryId
""")
    fun observePhotos(diaryId: Int): Flow<List<DiaryPhotoEntity>>

    // DELETE
    @Query("DELETE FROM diary_photo WHERE id = :photoId")
    suspend fun deleteById(photoId: Int)
}