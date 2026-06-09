package com.example.imperfect.feature.analysis.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ClassificationDto(

    val severity: String,

    @SerializedName("predicted_count")
    val predictedCount: Double,

    @SerializedName("severity_probabilities")
    val severityProbabilities: List<Double>
)