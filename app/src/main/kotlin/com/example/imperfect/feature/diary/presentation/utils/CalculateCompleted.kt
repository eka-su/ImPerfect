package com.example.imperfect.feature.diary.presentation.utils

import com.example.imperfect.feature.diary.domain.model.DiaryFullUi

fun calculateCompleted(day: DiaryFullUi?): Int {
    if (day == null) return 0

    return listOf(
        day.skincare.isNotEmpty(),

        false, // food
        false  // lifestyle и отслаьное потом
    ).count { it }
}