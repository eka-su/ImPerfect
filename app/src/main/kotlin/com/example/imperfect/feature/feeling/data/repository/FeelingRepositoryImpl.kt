package com.example.imperfect.feature.feeling.data.repository

import com.example.imperfect.feature.feeling.data.mapper.FeelingMapper
import com.example.imperfect.feature.feeling.data.source.FeelingLocalSource
import com.example.imperfect.feature.feeling.domain.model.FeelingForm
import com.example.imperfect.feature.feeling.domain.repository.FeelingRepository

class FeelingRepositoryImpl(
    private val localSource: FeelingLocalSource
) : FeelingRepository {

    override suspend fun getForm(
        diaryId: Int,
        screenCode: String
    ): FeelingForm {

        val screen =
            localSource.getScreen(screenCode)
                ?: error("Screen not found")

        val options = localSource.getOptions(screen.id)

        val selected =
            localSource.getSelected(
                diaryId,
                screen.id
            )

        val note =
            localSource
                .getNote(
                    diaryId,
                    screen.id
                )
                ?.notes
                .orEmpty()

        val result = FeelingMapper.map(
            screen,
            options,
            selected,
            note
        )

        return result
    }

    override suspend fun save(
        diaryId: Int,
        screenId: Int,
        selectedIds: Set<Int>,
        note: String
    ) {
        localSource.save(
            diaryId,
            screenId,
            selectedIds,
            note
        )
    }
}