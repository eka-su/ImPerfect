package com.example.imperfect.feature.diary.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.icon.IconAction
import com.example.imperfect.core.ui.designsystem.icon.IconSize
import com.example.imperfect.core.ui.designsystem.icon.IconStyles
import com.example.imperfect.core.ui.designsystem.theme.TextTitle
import com.example.imperfect.core.ui.kit.container.SheetContainer
import com.example.imperfect.core.ui.kit.icon.primitives.IconActionButton
import com.example.imperfect.feature.diary.presentation.utils.formatMonthYear
import java.time.LocalDate

@Composable
fun DiaryWeekCalendar(
    date: LocalDate,
    expanded: Boolean,
    markedDates: Set<LocalDate>,
    onPrevWeek: () -> Unit,
    onNextWeek: () -> Unit,
    onDateClick: (LocalDate) -> Unit,
    onExpandClick: () -> Unit
) {
    SheetContainer {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            // Header (месяц + стрелки)
            DiaryCalendarHeader(
                date = date,
                onPrevWeek = onPrevWeek,
                onNextWeek = onNextWeek
            )

            Spacer(Modifier.height(20.dp))

            // Дни недели
            WeekDaysRow()

            Spacer(Modifier.height(12.dp))

            // Календарь (week/month)
            DiaryCalendarContent(
                expanded = expanded,
                date = date,
                markedDates = markedDates,
                onDateClick = onDateClick
            )

            Spacer(Modifier.height(16.dp))

            // Кнопка раскрытия
            DiaryCalendarExpandHandle(
                onClick = onExpandClick
            )
        }
    }
}

@Composable
private fun DiaryCalendarHeader(
    date: LocalDate,
    onPrevWeek: () -> Unit,
    onNextWeek: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        CalendarContainer(
            size = IconSize.SMALL,
            style = IconStyles.Primary
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = formatMonthYear(date),
            style = MaterialTheme.typography.titleMedium,
            color = TextTitle,
            modifier = Modifier.weight(1f)
        )

        IconActionButton(
            action = IconAction.Back,
            onClick = onPrevWeek,
            size = IconSize.SMALL,
            style = IconStyles.Default
        )

        Spacer(Modifier.width(8.dp))

        IconActionButton(
            action = IconAction.Next,
            onClick = onNextWeek,
            size = IconSize.SMALL,
            style = IconStyles.Default
        )
    }
}

@Composable
private fun DiaryCalendarContent(
    expanded: Boolean,
    date: LocalDate,
    markedDates: Set<LocalDate>,
    onDateClick: (LocalDate) -> Unit
) {
    if (expanded) {
        MonthCalendar(
            selectedDate = date,
            markedDates = markedDates,
            onDateClick = onDateClick
        )
    } else {
        WeekDatesRow(
            selectedDate = date,
            markedDates = markedDates,
            onDateClick = onDateClick
        )
    }
}

@Composable
private fun DiaryCalendarExpandHandle(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(100))
                .background(Color(0xFFE0E0E0))
        )
    }
}