package com.example.imperfect.feature.skincare.domain.usecase

import com.example.imperfect.feature.skincare.domain.repository.SkincareRepository

class DeleteProductUseCase(
    private val repository: SkincareRepository
) {
    suspend operator fun invoke(
        productId: Int
    ) {
        repository.deleteProduct(productId)
    }
}