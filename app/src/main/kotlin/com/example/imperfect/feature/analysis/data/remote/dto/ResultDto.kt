package com.example.imperfect.feature.analysis.data.remote.dto

data class ResultDto(
    val classification: ClassificationDto,
    val localization: LocalizationDto,
    val ensemble: EnsembleDto
)