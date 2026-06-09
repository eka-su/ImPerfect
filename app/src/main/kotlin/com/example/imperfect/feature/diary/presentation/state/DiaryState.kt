package com.example.imperfect.feature.diary.presentation.state

import com.example.imperfect.feature.diary.domain.model.DiaryFullUi
import java.time.LocalDate

data class DiaryState(
    val selectedDate: LocalDate = LocalDate.now(),
    val day: DiaryFullUi? = null,
    val isLoading: Boolean = false,
    val expandedCalendar: Boolean = false, // открыт весь месяц или только неделя
    val markedDates: Set<LocalDate> = emptySet(), // даты, где есть фото или анализ
)
