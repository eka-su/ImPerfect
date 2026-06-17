package com.example.imperfect.feature.skincare.domain.usecase

import com.example.imperfect.feature.skincare.domain.repository.SkincareRepository

class AddProductToRoutineUseCase(
    private val repository: SkincareRepository
) {
    suspend operator fun invoke(
        diaryId: Int,
        productId: Int,
        timeIds: List<Int>
    ) {
        repository.addProductToRoutine(
            diaryId = diaryId,
            productId = productId,
            timeIds = timeIds
        )
    }
}