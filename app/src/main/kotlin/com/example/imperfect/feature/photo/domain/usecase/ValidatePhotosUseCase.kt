package com.example.imperfect.feature.photo.domain.usecase

import com.example.imperfect.core.constants.PhotoViewTypes
import com.example.imperfect.feature.photo.domain.model.Photo

class ValidatePhotosUseCase {

    operator fun invoke(photos: List<Photo>): Boolean {

        val types = photos.map { it.viewTypeId }.toSet()

        return types.containsAll(
            setOf(
                PhotoViewTypes.FRONT,
                PhotoViewTypes.LEFT,
                PhotoViewTypes.RIGHT
            )
        )
    }
}