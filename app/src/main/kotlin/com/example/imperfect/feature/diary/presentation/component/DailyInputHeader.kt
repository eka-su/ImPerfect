package com.example.imperfect.feature.diary.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.imperfect.core.ui.designsystem.theme.BlueLightBackground
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.designsystem.theme.TextTitle
import com.example.imperfect.core.ui.kit.chips.FlexibleChip
import com.example.imperfect.feature.diary.presentation.utils.formatProgress

@Composable
fun DailyInputHeader(
    completed: Int,
    total: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Заголовок
        DailyInputHeaderTitle(
            modifier = Modifier.weight(1f)
        )

        // Прогресс чип 0/5 заполнено
        DailyInputProgressChip(
            completed = completed,
            total = total
        )
    }
}

@Composable
private fun DailyInputHeaderTitle(
    modifier: Modifier = Modifier
) {
    Text(
        text = "Ежедневный ввод",
        modifier = modifier,
        style = MaterialTheme.typography.titleMedium,
        color = TextTitle
    )
}

@Composable
private fun DailyInputProgressChip(
    completed: Int,
    total: Int
) {
    FlexibleChip(
        backgroundColor = BlueLightBackground
    ) {
        Text(
            text = formatProgress(completed, total),
            style = MaterialTheme.typography.bodySmall,
            color = BluePrimary
        )
    }
}

