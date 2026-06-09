package com.example.imperfect.feature.analysis.domain.usecase

import com.example.imperfect.feature.analysis.domain.model.AnalysisResult
import com.example.imperfect.feature.analysis.domain.repository.AnalysisRepository
import com.example.imperfect.feature.photo.domain.model.Photo

class SaveAnalysisUseCase(
    private val repository: AnalysisRepository
) {
    suspend operator fun invoke(
        diaryId: Int,
        photos: List<Photo>,
        result: AnalysisResult
    ): Long {
        return repository.saveAnalysis(diaryId, photos, result)
    }
}