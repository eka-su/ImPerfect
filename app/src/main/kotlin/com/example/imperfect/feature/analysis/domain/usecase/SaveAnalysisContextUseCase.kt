package com.example.imperfect.feature.analysis.domain.usecase

import com.example.imperfect.feature.analysis.data.source.AnalysisContextLocalSource

class SaveAnalysisContextUseCase(
    private val source: AnalysisContextLocalSource
) {

    suspend operator fun invoke(
        multiAnalysisId: Int,
        hasMakeup: Boolean,
        note: String?
    ) {
        source.save(
            multiId = multiAnalysisId,
            hasMakeup = hasMakeup,
            note = note
        )
    }
}