package com.example.imperfect.feature.analysis.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary
import com.example.imperfect.core.ui.designsystem.theme.TextTitle
import com.example.imperfect.core.ui.kit.container.SheetContainer
import com.example.imperfect.feature.analysis.domain.model.AnalysisResult
import com.example.imperfect.feature.analysis.domain.interpretation.buildExtendedDescription

@Composable
fun AnalysisExtendedDescriptionCard(result: AnalysisResult) {

    val description = buildExtendedDescription(result)

    SheetContainer(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            AnalysisExtendedHeader()

            AnalysisExtendedBody(description)
        }
    }
}

@Composable
private fun AnalysisExtendedHeader() {
    Text(
        text = "Расширенное описание",
        style = MaterialTheme.typography.titleSmall,
        color = TextTitle
    )
}

@Composable
private fun AnalysisExtendedBody(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.labelMedium,
        color = TextSecondary
    )
}
