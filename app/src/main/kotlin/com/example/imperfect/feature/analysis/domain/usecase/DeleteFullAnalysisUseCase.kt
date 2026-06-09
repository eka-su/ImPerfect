package com.example.imperfect.feature.analysis.domain.usecase

import com.example.imperfect.feature.analysis.domain.repository.AnalysisRepository

class DeleteFullAnalysisUseCase(
    private val repository: AnalysisRepository
) {

    suspend operator fun invoke(multiAnalysisId: Int) {
        repository.deleteAnalysis(multiAnalysisId)
    }
}