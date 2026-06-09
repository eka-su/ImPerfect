package com.example.imperfect.feature.analysis.domain.usecase

import com.example.imperfect.feature.analysis.domain.model.AnalysisResult
import com.example.imperfect.feature.analysis.domain.repository.AnalysisRepository
import com.example.imperfect.feature.photo.domain.model.Photo

class GetSavedAnalysisUseCase(
    private val repository: AnalysisRepository
) {
    suspend operator fun invoke(
        analysisId: Int
    ): Pair<AnalysisResult, List<Photo>> {
        return repository.getSavedAnalysis(analysisId)
    }
}