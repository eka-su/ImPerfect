package com.example.imperfect.feature.photo.data.source

import com.example.imperfect.core.database.dao.PhotoDao
import com.example.imperfect.core.database.entity.DiaryPhotoEntity

class PhotoLocalSource(
    private val photoDao: PhotoDao
) {

    suspend fun insert(photo: DiaryPhotoEntity): Long {
        return photoDao.insertPhoto(photo)
    }

    suspend fun getByDiary(diaryId: Int): List<DiaryPhotoEntity> {
        return photoDao.getPhotos(diaryId)
    }

    suspend fun delete(photoId: Int) {
        photoDao.deleteById(photoId)
    }
}