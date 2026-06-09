package com.example.imperfect.feature.diary.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.theme.BlueLightBackground
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary
import com.example.imperfect.core.ui.designsystem.theme.TextTitle
import com.example.imperfect.core.ui.kit.container.SheetContainer

@Composable
fun DiaryProgressCard(
    progress: Int
) {
    SheetContainer(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            DiaryProgressHeader()

            Spacer(Modifier.height(16.dp))

            DiaryProgressBar(progress)

            Spacer(Modifier.height(12.dp))

            DiaryProgressFooter(progress)
        }
    }
}

@Composable
private fun DiaryProgressHeader() {
    Text(
        text = "Прогресс",
        style = MaterialTheme.typography.titleMedium,
        color = TextTitle
    )
}

@Composable
private fun DiaryProgressBar(progress: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .clip(RoundedCornerShape(100.dp))
            .background(BlueLightBackground)
    ) {

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progress / 100f)
                .clip(RoundedCornerShape(100.dp))
                .background(BluePrimary)
        )
    }
}

@Composable
private fun DiaryProgressFooter(progress: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Прогресс",
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary
        )

        Spacer(Modifier.weight(1f))

        Text(
            text = "$progress/100%",
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary
        )
    }
}