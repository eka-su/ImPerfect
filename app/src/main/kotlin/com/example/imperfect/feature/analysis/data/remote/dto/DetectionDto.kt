package com.example.imperfect.feature.analysis.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DetectionDto(

    @SerializedName("class_id")
    val classId: Int,

    val bbox: List<Double>,

    val confidence: Double
)