package com.example.imperfect.feature.skincare.domain.usecase

import com.example.imperfect.feature.skincare.domain.repository.SkincareRepository

class GetRoutineHistoryUseCase(
    private val repository: SkincareRepository
) {
    operator fun invoke(
        fromDate: String,
        toDate: String
    ) = repository.getRoutineHistory(
        fromDate,
        toDate
    )
}