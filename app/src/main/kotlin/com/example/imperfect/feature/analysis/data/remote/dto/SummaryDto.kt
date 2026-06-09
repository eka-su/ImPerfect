package com.example.imperfect.feature.analysis.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SummaryDto(

    @SerializedName("final_severity")
    val finalSeverity: String,

    @SerializedName("average_acne_count")
    val averageAcneCount: Double,

    @SerializedName("total_detections")
    val totalDetections: Int,

    @SerializedName("average_confidence")
    val averageConfidence: Double
)