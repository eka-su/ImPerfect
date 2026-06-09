package com.example.imperfect.feature.photo.domain.usecase

import com.example.imperfect.feature.photo.domain.model.Photo
import com.example.imperfect.feature.photo.domain.repository.PhotoRepository

class GetPhotosUseCase(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(diaryId: Int): List<Photo> {
        return repository.getPhotos(diaryId)
    }
}