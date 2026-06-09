package com.example.imperfect.feature.photo.domain.usecase

import com.example.imperfect.feature.photo.domain.model.PhotoViewType
import com.example.imperfect.feature.photo.domain.repository.LookupRepository

class GetPhotoTypesUseCase(
    private val repository: LookupRepository
) {

    suspend operator fun invoke(): List<PhotoViewType> {
        return repository.getPhotoTypes()
    }
}