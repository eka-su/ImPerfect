package com.example.imperfect.feature.analysis.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary
import com.example.imperfect.core.ui.designsystem.theme.TextTitle
import com.example.imperfect.core.ui.kit.container.SheetContainer
import com.example.imperfect.feature.analysis.domain.model.AnalysisResult
import com.example.imperfect.feature.analysis.presentation.mapper.acneTypeUi
import androidx.compose.runtime.remember

@Composable
fun AcneSummaryCard(result: AnalysisResult) {

    val ui = remember(result) {
        result.toUi()
    }

    SheetContainer(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row {
                Text(
                    text = "Всего насчитано ",
                    style = MaterialTheme.typography.titleSmall,
                    color = TextTitle
                )

                Text(
                    text = ui.total.toString(),
                    style = MaterialTheme.typography.titleSmall,
                    color = BluePrimary
                )

                Text(
                    text = " элементов",
                    style = MaterialTheme.typography.titleSmall,
                    color = TextTitle
                )
            }

            Spacer(Modifier.height(6.dp))

            Row {
                Text(
                    text = "Самая популярная — ",
                    style = MaterialTheme.typography.labelMedium,
                    color = TextSecondary
                )

                Text(
                    text = ui.mostCommonName,
                    style = MaterialTheme.typography.labelMedium,
                    color = BluePrimary
                )
            }
        }
    }
}

data class AcneSummaryUi(
    val total: Int,
    val mostCommonName: String
)

fun AnalysisResult.toUi(): AcneSummaryUi {

    val grouped = images
        .flatMap { it.detections }
        .groupBy { it.classId }

    val mostCommonClassId = grouped
        .maxByOrNull { it.value.size }
        ?.key

    val mostCommonName = acneTypeUi(mostCommonClassId ?: 0, 0).name

    return AcneSummaryUi(
        total = summary.totalDetections,
        mostCommonName = mostCommonName
    )
}

