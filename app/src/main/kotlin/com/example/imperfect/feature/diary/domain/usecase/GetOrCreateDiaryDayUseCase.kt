package com.example.imperfect.feature.diary.domain.usecase

import com.example.imperfect.feature.diary.domain.model.DiaryFullUi
import com.example.imperfect.feature.diary.domain.repository.DiaryRepository
import java.time.LocalDate

class GetOrCreateDiaryDayUseCase(
    private val repository: DiaryRepository
) {
    suspend operator fun invoke(date: LocalDate): DiaryFullUi {
        return repository.getOrCreateDay(date)
    }
}