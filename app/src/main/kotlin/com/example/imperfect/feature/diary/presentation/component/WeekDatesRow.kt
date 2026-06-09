package com.example.imperfect.feature.diary.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.designsystem.theme.TextTitle
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun WeekDatesRow(
    selectedDate: LocalDate,
    markedDates: Set<LocalDate>,
    onDateClick: (LocalDate) -> Unit
) {
    val monday = selectedDate.with(DayOfWeek.MONDAY)

    Row(modifier = Modifier.fillMaxWidth()) {

        repeat(7) { index ->

            val day = monday.plusDays(index.toLong())

            WeekDayCell(
                modifier = Modifier.weight(1f),
                day = day,
                selected = day == selectedDate,
                marked = day in markedDates,
                onClick = { onDateClick(day) }
            )
        }
    }
}

@Composable
private fun WeekDayCell(
    modifier: Modifier = Modifier,
    day: LocalDate,
    selected: Boolean,
    marked: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        DayNumber(day, selected)

        Spacer(Modifier.height(4.dp))

        // Точка где ты
        DayMarker(marked)
    }
}

@Composable
private fun DayNumber(
    day: LocalDate,
    selected: Boolean
) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) BluePrimary else Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.dayOfMonth.toString(),
            color = if (selected) Color.White else TextTitle
        )
    }
}

@Composable
private fun DayMarker(marked: Boolean) {
    if (!marked) return

    Box(
        modifier = Modifier
            .size(6.dp)
            .background(Color.Black, CircleShape)
    )
}