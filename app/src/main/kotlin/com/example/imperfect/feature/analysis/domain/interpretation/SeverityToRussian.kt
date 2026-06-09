package com.example.imperfect.feature.analysis.domain.interpretation

fun severityToRussian(severity: String): String {
    return when (severity.lowercase()) {
        "mild" -> "Лёгкая степень"
        "moderate" -> "Средняя степень"
        "severe" -> "Тяжёлая степень"
        else -> "Неизвестно"
    }
}