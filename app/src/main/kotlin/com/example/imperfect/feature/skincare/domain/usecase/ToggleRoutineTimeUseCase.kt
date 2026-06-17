package com.example.imperfect.feature.skincare.domain.usecase

import com.example.imperfect.feature.skincare.domain.model.CareRoutine
import com.example.imperfect.feature.skincare.domain.repository.SkincareRepository

class ToggleRoutineTimeUseCase(
    private val repository: SkincareRepository
) {

    suspend operator fun invoke(
        routine: CareRoutine?,
        productId: Int,
        diaryId: Int,
        timeId: Int
    ) {

        if (routine == null) {

            repository.addProductToRoutine(
                diaryId,
                productId,
                listOf(timeId)
            )

            return
        }

        val hasTime = routine.times.any {
            it.id == timeId
        }

        if (hasTime) {

            repository.removeTimeFromRoutine(
                routine.id,
                timeId
            )

        } else {

            repository.addTimeToRoutine(
                routine.id,
                timeId
            )
        }
    }
}