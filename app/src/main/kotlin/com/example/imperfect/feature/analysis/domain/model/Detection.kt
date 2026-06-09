package com.example.imperfect.feature.analysis.domain.model

data class Detection(
    val classId: Int,
    val x: Double,
    val y: Double,
    val width: Double,
    val height: Double,
    val confidence: Double
)