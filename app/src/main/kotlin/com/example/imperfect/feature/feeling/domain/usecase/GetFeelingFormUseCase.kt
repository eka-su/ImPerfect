package com.example.imperfect.feature.feeling.domain.usecase

import com.example.imperfect.feature.feeling.domain.repository.FeelingRepository

class GetFeelingFormUseCase(
    private val repository: FeelingRepository
) {

    suspend operator fun invoke(
        diaryId: Int,
        screenCode: String
    ) =
        repository.getForm(
            diaryId,
            screenCode
        )
}