package com.example.imperfect.feature.diary.domain.repository

import com.example.imperfect.feature.diary.domain.model.DiaryFullUi
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface DiaryRepository {

    suspend fun getOrCreateDay(date: LocalDate): DiaryFullUi

    fun observeDay(date: LocalDate): Flow<DiaryFullUi>

    suspend fun getMarkedDates(): Set<LocalDate>

    fun observeMarkedDates(): Flow<Set<LocalDate>>
}