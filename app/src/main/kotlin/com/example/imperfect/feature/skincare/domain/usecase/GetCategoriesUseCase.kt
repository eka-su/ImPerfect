package com.example.imperfect.feature.skincare.domain.usecase

import com.example.imperfect.feature.skincare.domain.repository.SkincareRepository

class GetCategoriesUseCase(
    private val repository: SkincareRepository
) {
    operator fun invoke() =
        repository.getCategories()
}