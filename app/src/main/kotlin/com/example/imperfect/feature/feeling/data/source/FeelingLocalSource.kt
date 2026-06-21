package com.example.imperfect.feature.feeling.data.source

import com.example.imperfect.core.database.dao.FeelingDao

class FeelingLocalSource(
    private val dao: FeelingDao
) {

    suspend fun getScreen(
        code: String
    ) = dao.getScreenByCode(code)

    suspend fun getOptions(
        screenId: Int
    ) = dao.getOptions(screenId)

    suspend fun getSelected(
        diaryId: Int,
        screenId: Int
    ) = dao.getSelectedOptionIds(
        diaryId,
        screenId
    )

    suspend fun getNote(
        diaryId: Int,
        screenId: Int
    ) = dao.getNote(
        diaryId,
        screenId
    )

    suspend fun save(
        diaryId: Int,
        screenId: Int,
        selectedIds: Set<Int>,
        note: String
    ) {
        dao.saveScreenState(
            diaryId,
            screenId,
            selectedIds,
            note
        )
    }
}