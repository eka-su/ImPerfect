package com.example.imperfect.feature.feeling.domain.repository

import com.example.imperfect.feature.feeling.domain.model.FeelingForm

interface FeelingRepository {

    suspend fun getForm(
        diaryId: Int,
        screenCode: String
    ): FeelingForm

    suspend fun save(
        diaryId: Int,
        screenId: Int,
        selectedIds: Set<Int>,
        note: String
    )
}