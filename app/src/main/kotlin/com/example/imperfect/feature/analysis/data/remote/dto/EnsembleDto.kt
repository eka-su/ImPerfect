package com.example.imperfect.feature.analysis.data.remote.dto

import com.google.gson.annotations.SerializedName

data class EnsembleDto(

    @SerializedName("final_severity")
    val finalSeverity: String,

    @SerializedName("final_count")
    val finalCount: Int,

    @SerializedName("confidence_score")
    val confidenceScore: Double
)