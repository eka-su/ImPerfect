package com.example.imperfect.feature.photo.domain.usecase

import com.example.imperfect.feature.photo.domain.model.Photo
import com.example.imperfect.feature.photo.domain.model.PhotoViewType

class ValidatePhotosUseCase {

    operator fun invoke(
        photos: List<Photo>,
        requiredTypes: List<PhotoViewType>
    ): Boolean {

        val existing = photos.map { it.viewTypeId }.toSet()

        return requiredTypes.all { it.id in existing }
    }
}