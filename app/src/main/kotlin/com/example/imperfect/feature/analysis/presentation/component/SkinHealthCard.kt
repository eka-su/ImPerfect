package com.example.imperfect.feature.analysis.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imperfect.R
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary
import com.example.imperfect.core.ui.designsystem.theme.TextTitle
import com.example.imperfect.core.ui.designsystem.theme.Yellow
import com.example.imperfect.core.ui.kit.container.SheetContainer
import com.example.imperfect.feature.analysis.domain.model.AnalysisResult
import com.example.imperfect.feature.analysis.domain.model.Summary
import com.example.imperfect.feature.analysis.presentation.state.SkinHealthState

@Composable
fun SkinHealthCard(result: AnalysisResult) {

    val state = remember(result) {
        SkinHealthState.from(result)
    }

    SheetContainer(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            SkinHealthInfoBlock(
                modifier = Modifier.weight(2f),
                state = state
            )

            Spacer(Modifier.width(8.dp))

            SkinHealthProgressCircle(
                modifier = Modifier.weight(1f),
                percent = state.percent
            )
        }
    }
}

@Composable
private fun SkinHealthInfoBlock(
    modifier: Modifier = Modifier,
    state: SkinHealthState
) {
    Column(modifier = modifier) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Icon(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = null,
                tint = Yellow,
                modifier = Modifier.size(20.dp)
            )

            Spacer(Modifier.width(6.dp))

            Text(
                text = "Индекс здоровья",
                style = MaterialTheme.typography.titleMedium,
                color = BluePrimary
            )
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = state.severityRu,
            style = MaterialTheme.typography.titleSmall,
            color = TextTitle
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = state.description,
            style = MaterialTheme.typography.labelMedium,
            color = TextSecondary
        )
    }
}

@Composable
private fun SkinHealthProgressCircle(
    modifier: Modifier = Modifier,
    percent: Int
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(
                        width = 8.dp,
                        color = BluePrimary,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "$percent%",
                    color = BluePrimary,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SkinHealthCardPreview() {

    val fakeResult = AnalysisResult(
        summary = Summary(
            finalSeverity = "moderate",
            averageAcneCount = 12.0,
            totalDetections = 10,
            averageConfidence = 0.87
        ),
        images = emptyList()
    )

    SkinHealthCard(result = fakeResult)
}
