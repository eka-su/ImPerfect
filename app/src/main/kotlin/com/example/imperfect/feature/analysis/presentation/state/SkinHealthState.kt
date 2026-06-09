package com.example.imperfect.feature.analysis.presentation.state

import com.example.imperfect.feature.analysis.domain.interpretation.calculateSkinHealthPercent
import com.example.imperfect.feature.analysis.domain.interpretation.severityToRussian
import com.example.imperfect.feature.analysis.domain.interpretation.skinHealthDescription
import com.example.imperfect.feature.analysis.domain.model.AnalysisResult

data class SkinHealthState(
    val percent: Int,
    val severityRu: String,
    val description: String
) {
    companion object {

        fun from(result: AnalysisResult): SkinHealthState {

            val total = result.summary.totalDetections
            val percent = calculateSkinHealthPercent(total)
            val severityRu = severityToRussian(result.summary.finalSeverity)
            val description = skinHealthDescription(
                result.summary.finalSeverity,
                percent
            )

            return SkinHealthState(
                percent = percent,
                severityRu = severityRu,
                description = description
            )
        }
    }
}