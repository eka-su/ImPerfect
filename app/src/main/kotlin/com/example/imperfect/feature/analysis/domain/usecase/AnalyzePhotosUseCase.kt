package com.example.imperfect.feature.analysis.domain.usecase

import com.example.imperfect.feature.analysis.domain.model.AnalysisResult
import com.example.imperfect.feature.analysis.domain.repository.AnalysisRepository
import com.example.imperfect.feature.photo.domain.model.Photo

class AnalyzePhotosUseCase(
    private val repository: AnalysisRepository
) {

    suspend operator fun invoke(
        photos: List<Photo>
    ): AnalysisResult {

        return repository.analyzePhotos(photos)
    }
}