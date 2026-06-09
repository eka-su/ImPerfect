package com.example.imperfect.feature.diary.presentation.utils

import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

fun formatMonthYear(date: LocalDate): String {
    return date.month.getDisplayName(
        TextStyle.FULL,
        Locale("ru")
    ).replaceFirstChar { it.uppercase() } + " ${date.year}"
}