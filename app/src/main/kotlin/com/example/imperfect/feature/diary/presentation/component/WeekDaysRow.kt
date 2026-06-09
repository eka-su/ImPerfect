package com.example.imperfect.feature.diary.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary

@Composable
fun WeekDaysRow() {

    val days = listOf("Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс")

    Row(modifier = Modifier.fillMaxWidth()) {

        days.forEach { day ->

            WeekDayLabel(
                day = day,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun WeekDayLabel(
    day: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = day,
        modifier = modifier,
        textAlign = TextAlign.Center,
        color = TextSecondary,
        style = MaterialTheme.typography.bodySmall
    )
}