package com.example.imperfect.feature.analysis.domain.interpretation

fun calculateSkinHealthPercent(totalDetections: Int): Int {
    return when {
        totalDetections <= 5 -> 95 - (totalDetections * 5)
        totalDetections <= 20 -> 70 - (totalDetections * 2)
        else -> (20 - totalDetections).coerceAtLeast(5)
    }.coerceIn(0, 100)
}