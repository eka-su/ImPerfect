package com.example.imperfect.feature.analysis.domain.repository

import com.example.imperfect.feature.analysis.domain.model.AnalysisResult
import com.example.imperfect.feature.photo.domain.model.Photo

interface AnalysisRepository {

    suspend fun analyzePhotos(
        photos: List<Photo>
    ): AnalysisResult

    suspend fun saveAnalysis(
        diaryId: Int,
        photos: List<Photo>,
        result: AnalysisResult
    ): Long

    suspend fun deleteAnalysis(
        multiAnalysisId: Int
    )

    suspend fun getSavedAnalysis(
        analysisId: Int
    ): Pair<AnalysisResult, List<Photo>>
}