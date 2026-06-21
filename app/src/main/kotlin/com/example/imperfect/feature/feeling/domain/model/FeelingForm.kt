package com.example.imperfect.feature.feeling.domain.model

data class FeelingForm(
    val screenId: Int,
    val title: String,
    val options: List<FeelingOption>,
    val note: String
)