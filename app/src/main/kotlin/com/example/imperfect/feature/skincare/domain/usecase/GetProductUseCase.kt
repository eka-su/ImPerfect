package com.example.imperfect.feature.skincare.domain.usecase

import com.example.imperfect.feature.skincare.domain.repository.SkincareRepository

class GetProductUseCase(
    private val repository: SkincareRepository
) {
    suspend operator fun invoke(
        id: Int
    ) = repository.getProductById(id)
}

