package com.example.imperfect.feature.skincare.domain.usecase

import com.example.imperfect.feature.skincare.domain.repository.SkincareRepository

class GetTodayRoutineUseCase(
    private val repository: SkincareRepository
) {
    operator fun invoke(diaryId: Int) =
        repository.getTodayRoutine(diaryId)
}