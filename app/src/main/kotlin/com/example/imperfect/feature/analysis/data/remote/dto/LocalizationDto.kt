package com.example.imperfect.feature.analysis.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LocalizationDto(

    val detections: List<DetectionDto>,

    @SerializedName("detected_count")
    val detectedCount: Int
)