package com.example.imperfect.feature.photo.domain.usecase

import com.example.imperfect.feature.photo.domain.model.Photo
import com.example.imperfect.feature.photo.domain.repository.PhotoRepository

class AddPhotoUseCase(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(photo: Photo): Long {
        return repository.addPhoto(photo)
    }
}