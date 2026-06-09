package com.example.imperfect.feature.analysis.presentation.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatScanDate(timestamp: String): String {

    val millis = timestamp.toLongOrNull() ?: return ""

    val now = System.currentTimeMillis()
    val diff = now - millis

    val day = 24 * 60 * 60 * 1000

    return when {
        diff < day -> "Сегодня, ${SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(millis))}"
        diff < day * 2 -> "Вчера, ${SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(millis))}"
        else -> SimpleDateFormat("dd MMM, HH:mm", Locale.getDefault()).format(Date(millis))
    }
}