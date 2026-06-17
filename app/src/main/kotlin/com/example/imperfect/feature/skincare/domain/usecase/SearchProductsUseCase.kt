package com.example.imperfect.feature.skincare.domain.usecase

import com.example.imperfect.feature.skincare.domain.repository.SkincareRepository

class SearchProductsUseCase(
    private val repository: SkincareRepository
) {
    operator fun invoke(query: String) =
        repository.searchProducts(query)
}