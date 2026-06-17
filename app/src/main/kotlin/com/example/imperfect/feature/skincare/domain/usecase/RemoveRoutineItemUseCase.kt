package com.example.imperfect.feature.skincare.domain.usecase

import com.example.imperfect.feature.skincare.domain.repository.SkincareRepository

class RemoveRoutineItemUseCase(
    private val repository: SkincareRepository
) {
    suspend operator fun invoke(
        routineId: Int
    ) {
        repository.removeFromRoutine(routineId)
    }
}