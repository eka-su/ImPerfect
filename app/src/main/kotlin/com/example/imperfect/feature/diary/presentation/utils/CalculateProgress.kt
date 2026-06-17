package com.example.imperfect.feature.diary.presentation.utils

import com.example.imperfect.feature.diary.domain.model.DiaryFullUi

fun calculateProgress(day: DiaryFullUi?): Int {
    if (day == null) return 0

    val done = listOf(
        day.photos.isNotEmpty(),
        day.analysisId != null,
        day.skincare.isNotEmpty(),

        false, // food
        false  // lifestyle и др
    ).count { it }

    return done * 100 / 5
}