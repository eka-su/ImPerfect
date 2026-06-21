package com.example.imperfect.feature.feeling.domain.usecase

import com.example.imperfect.feature.feeling.domain.repository.FeelingRepository

class SaveFeelingFormUseCase(
    private val repository: FeelingRepository
) {

    suspend operator fun invoke(
        diaryId: Int,
        screenId: Int,
        selectedIds: Set<Int>,
        note: String
    ) {
        repository.save(
            diaryId,
            screenId,
            selectedIds,
            note
        )
    }
}