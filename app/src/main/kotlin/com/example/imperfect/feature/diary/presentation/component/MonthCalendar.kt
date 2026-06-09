package com.example.imperfect.feature.diary.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import java.time.LocalDate

@Composable
fun MonthCalendar(
    selectedDate: LocalDate,
    markedDates: Set<LocalDate>,
    onDateClick: (LocalDate) -> Unit
) {

    val firstDay = selectedDate.withDayOfMonth(1)
    val daysInMonth = selectedDate.lengthOfMonth()

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        userScrollEnabled = false,
        modifier = Modifier.height(260.dp)
    ) {

        items(daysInMonth) { index ->

            val day = firstDay.plusDays(index.toLong())

            CalendarDayCell(
                day = day,
                selected = day == selectedDate,
                marked = day in markedDates,
                onClick = { onDateClick(day) }
            )
        }
    }
}

@Composable
private fun CalendarDayCell(
    day: LocalDate,
    selected: Boolean,
    marked: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CalendarDayNumber(
            day = day,
            selected = selected
        )

        Spacer(Modifier.height(4.dp))

        // Точка, елси запись
        CalendarDayMarker(marked)
    }
}

@Composable
private fun CalendarDayNumber(
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
private fun CalendarDayMarker(
    marked: Boolean
) {
    if (!marked) return

    Box(
        modifier = Modifier
            .size(6.dp)
            .background(Color.Black, CircleShape)
    )
}
