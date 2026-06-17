package com.example.imperfect.feature.skincare.domain.usecase

import com.example.imperfect.feature.skincare.domain.repository.SkincareRepository

class RemoveTimeFromRoutineUseCase(
    private val repository: SkincareRepository
) {
    suspend operator fun invoke(
        routineId: Int,
        timeId: Int
    ) {
        repository.removeTimeFromRoutine(routineId, timeId)
    }
}