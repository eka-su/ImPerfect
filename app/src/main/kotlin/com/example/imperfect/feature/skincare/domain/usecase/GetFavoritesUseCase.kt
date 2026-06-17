package com.example.imperfect.feature.skincare.domain.usecase

import com.example.imperfect.feature.skincare.domain.repository.SkincareRepository

class GetFavoritesUseCase(
    private val repository: SkincareRepository
) {
    operator fun invoke() =
        repository.getFavorites()
}