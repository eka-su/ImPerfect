package com.example.imperfect.feature.diary.data.mapper

import com.example.imperfect.core.database.entity.SkinDiaryDayEntity
import com.example.imperfect.feature.diary.domain.model.DiaryDayUi

fun SkinDiaryDayEntity.toDomain(): DiaryDayUi {
    return DiaryDayUi(
        id = id,
        userId = userId,
        date = date
    )
}

