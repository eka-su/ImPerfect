package com.example.imperfect.feature.skincare.domain.usecase

import com.example.imperfect.feature.skincare.domain.repository.SkincareRepository

class GetProductsUseCase(
    private val repository: SkincareRepository
) {
    operator fun invoke() =
        repository.getProducts()
}