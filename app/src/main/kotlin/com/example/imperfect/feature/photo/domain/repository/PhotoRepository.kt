package com.example.imperfect.feature.photo.domain.repository

import com.example.imperfect.feature.photo.domain.model.Photo

interface PhotoRepository {

    suspend fun addPhoto(photo: Photo): Long

    suspend fun getPhotos(diaryId: Int): List<Photo>

    suspend fun deletePhoto(photoId: Int)
}