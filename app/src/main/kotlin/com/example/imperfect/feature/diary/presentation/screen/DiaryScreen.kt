package com.example.imperfect.feature.diary.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.navigation.Router
import com.example.imperfect.core.ui.designsystem.icon.IconAction
import com.example.imperfect.feature.diary.presentation.component.DailyInputCard
import com.example.imperfect.feature.diary.presentation.component.DailyInputHeader
import com.example.imperfect.feature.diary.presentation.component.DiaryProgressCard
import com.example.imperfect.feature.diary.presentation.component.DiaryTopBar
import com.example.imperfect.feature.diary.presentation.component.DiaryWeekCalendar
import com.example.imperfect.feature.diary.presentation.component.SkinAnalysisCard
import com.example.imperfect.feature.diary.presentation.viewmodel.DiaryViewModel

//тут тоже правильно логику повыносить
@Composable
fun DiaryScreen(
    viewModel: DiaryViewModel,
    router: Router
) {
    val state by viewModel.state.collectAsState()
    val day = state.day

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 20.dp,
            end = 20.dp,
            top = 20.dp,
            bottom = 20.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){

        item {
            DiaryTopBar(
                onCurrentDayClick = {
                    viewModel.goToToday()
                },
                onGalleryClick = { }
            )
        }

        item {

            DiaryWeekCalendar(
                date = state.selectedDate,
                expanded = state.expandedCalendar,
                markedDates = state.markedDates,

                onPrevWeek = viewModel::prevWeek,
                onNextWeek = viewModel::nextWeek,

                onDateClick = viewModel::selectDate,

                onExpandClick = viewModel::toggleCalendar
            )
        }

        item {
            DiaryProgressCard(
                progress = when {

                    day?.photos?.isNotEmpty() == true &&
                            day.analysisId != null -> 40

                    day?.photos?.isNotEmpty() == true -> 20

                    day?.analysisId != null -> 20

                    else -> 0
                }
            )
        }

        item {
            SkinAnalysisCard(
                percent = day?.analysisPercent,
                photos = day?.photos ?: emptyList(),
                onClick = {
                    day?.analysisId?.let {
                        router.openAnalysisById(it)
                    } ?: router.openPhotoFlow()
                }
            )
        }

        item {
            DailyInputHeader(
                completed = 0,
                total = 5
            )
        }

        item {
            DailyInputCard(
                icon = IconAction.Skincare,
                title = "Уход за кожей",
                subtitle = "Добавь свои средства ухода",
                onClick = { }
            )
        }

        item {
            DailyInputCard(
                icon = IconAction.Food,
                title = "Питание",
                subtitle = "Добавь свои продукты питания",
                onClick = { }
            )
        }

        item {
            DailyInputCard(
                icon = IconAction.Skin,
                title = "Ощущение кожи",
                subtitle = "Опиши как чувствует твоя кожа",
                onClick = { }
            )
        }

        item {
            DailyInputCard(
                icon = IconAction.Health,
                title = "Здоровье и чувства",
                subtitle = "Опиши как ты себя чувствуешь",
                onClick = { }
            )
        }

        item {
            DailyInputCard(
                icon = IconAction.Lifestyle,
                title = "Образ жизни",
                subtitle = "Укажи доп. факторы твоей жизни",
                onClick = { }
            )
        }
    }

}
