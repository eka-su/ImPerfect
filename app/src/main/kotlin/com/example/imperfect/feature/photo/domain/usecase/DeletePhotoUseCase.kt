package com.example.imperfect.feature.photo.domain.usecase

import com.example.imperfect.feature.photo.domain.repository.PhotoRepository

class DeletePhotoUseCase(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(photoId: Int) {
        repository.deletePhoto(photoId)
    }
}