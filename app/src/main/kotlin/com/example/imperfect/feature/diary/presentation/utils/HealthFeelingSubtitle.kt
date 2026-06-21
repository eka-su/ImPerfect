package com.example.imperfect.feature.diary.presentation.utils

import com.example.imperfect.feature.diary.domain.model.DiaryFullUi

fun DiaryFullUi?.healthFeelingSubtitle(): String {
    val list = this?.healthFeeling ?: return "Опиши как ты себя чувствуешь"

    if (list.isEmpty()) return "Опиши как ты себя чувствуешь"

    val first = list.first()

    return when {
        list.size == 1 -> first
        list.size == 2 -> "$first, ${list[1]}"
        else -> "$first, ${list[1]} +${list.size - 2}"
    }
}