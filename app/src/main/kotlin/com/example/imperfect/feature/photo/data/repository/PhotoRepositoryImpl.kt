package com.example.imperfect.feature.photo.data.repository

import com.example.imperfect.feature.photo.data.mapper.toDomain
import com.example.imperfect.feature.photo.data.mapper.toEntity
import com.example.imperfect.feature.photo.data.source.PhotoLocalSource
import com.example.imperfect.feature.photo.domain.model.Photo
import com.example.imperfect.feature.photo.domain.repository.PhotoRepository

class PhotoRepositoryImpl(
    private val localSource: PhotoLocalSource
) : PhotoRepository {

    override suspend fun addPhoto(photo: Photo): Long {
        return localSource.insert(photo.toEntity())
    }

    override suspend fun getPhotos(diaryId: Int): List<Photo> {
        return localSource.getByDiary(diaryId).map { it.toDomain() }
    }

    override suspend fun deletePhoto(photoId: Int) {
        localSource.delete(photoId)
    }
}