package com.example.imperfect.feature.analysis.domain.usecase

import com.example.imperfect.feature.analysis.data.source.AnalysisContextLocalSource

class GetAnalysisContextUseCase(
    private val source: AnalysisContextLocalSource
) {
    suspend operator fun invoke(multiId: Int) =
        source.get(multiId)
}