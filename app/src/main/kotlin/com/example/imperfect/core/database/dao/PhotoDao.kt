package com.example.imperfect.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.imperfect.core.database.entity.DiaryPhotoEntity

@Dao
interface PhotoDao {

    @Insert
    suspend fun insertPhoto(photo: DiaryPhotoEntity): Long

    @Query("SELECT * FROM diary_photo WHERE diary_id = :diaryId")
    suspend fun getPhotos(diaryId: Int): List<DiaryPhotoEntity>

    @Query("DELETE FROM diary_photo WHERE id = :photoId")
    suspend fun deleteById(photoId: Int)
}