package com.example.imperfect.feature.diary.presentation.utils

import com.example.imperfect.feature.diary.domain.model.DiaryFullUi

//потом с остальными в 1 сгрупировать
fun DiaryFullUi?.skinFeelingSubtitle(): String {
    val list = this?.skinFeeling ?: return "Опиши как чувствует твоя кожа"

    if (list.isEmpty()) return "Опиши как чувствует твоя кожа"

    val first = list.first()

    return when {
        list.size == 1 -> first
        list.size == 2 -> "$first, ${list[1]}"
        else -> "$first, ${list[1]} +${list.size - 2}"
    }
}