package com.example.imperfect.feature.analysis.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AnalyzeMultiResponse(

    val summary: SummaryDto,

    @SerializedName("per_image_results")
    val perImageResults: List<PerImageResultDto>
)