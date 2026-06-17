package com.example.imperfect.feature.skincare.domain.usecase

import com.example.imperfect.feature.skincare.domain.model.CareProduct
import com.example.imperfect.feature.skincare.domain.repository.SkincareRepository

class ToggleFavoriteUseCase(
    private val repository: SkincareRepository
) {
    suspend operator fun invoke(
        product: CareProduct
    ) {
        repository.updateProduct(
            product.copy(
                isFavorite = !product.isFavorite
            )
        )
    }
}

