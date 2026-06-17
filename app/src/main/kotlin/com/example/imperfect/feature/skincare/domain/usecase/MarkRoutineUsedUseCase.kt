package com.example.imperfect.feature.skincare.domain.usecase

import com.example.imperfect.feature.skincare.domain.repository.SkincareRepository

class MarkRoutineUsedUseCase(
    private val repository: SkincareRepository
) {
    suspend operator fun invoke(
        routineId: Int,
        used: Boolean
    ) {
        repository.markRoutineUsed(
            routineId,
            used
        )
    }
}