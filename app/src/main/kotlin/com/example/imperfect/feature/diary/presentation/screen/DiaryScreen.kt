package com.example.imperfect.feature.diary.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
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
import com.example.imperfect.feature.diary.presentation.model.DiaryInputItem
import com.example.imperfect.feature.diary.presentation.utils.calculateCompleted
import com.example.imperfect.feature.diary.presentation.utils.calculateProgress
import com.example.imperfect.feature.diary.presentation.utils.skinFeelingSubtitle
import com.example.imperfect.feature.diary.presentation.utils.skincareSubtitle
import com.example.imperfect.feature.diary.presentation.viewmodel.DiaryViewModel
import com.example.imperfect.feature.diary.presentation.utils.healthFeelingSubtitle


@Composable
fun DiaryScreen(
    viewModel: DiaryViewModel,
    router: Router
) {
    val state by viewModel.state.collectAsState()
    val day = state.day

    val completed = calculateCompleted(day)
    val progress = calculateProgress(day)

    val inputItems = listOf(
        DiaryInputItem(
            icon = IconAction.Skincare,
            title = "Уход за кожей",
            subtitle = day?.skincareSubtitle() ?: "",
            onClick = {
                day?.id?.let { router.openSkincare(it) }
            }
        ),
        DiaryInputItem(
            icon = IconAction.Food,
            title = "Питание",
            subtitle = "Добавь свои продукты питания",
            onClick = { }
        ),
        DiaryInputItem(
            icon = IconAction.Skin,
            title = "Ощущение кожи",
            subtitle = day?.skinFeelingSubtitle() ?: "",
            onClick = {  day?.id?.let { router.openSkinFeeling(it) }
            }
        ),
        DiaryInputItem(
            icon = IconAction.Health,
            title = "Здоровье и чувства",
            subtitle = day?.healthFeelingSubtitle() ?: "",
            onClick = {   day?.id?.let { router.openHealthFeeling(it) }}
        ),
        DiaryInputItem(
            icon = IconAction.Lifestyle,
            title = "Образ жизни",
            subtitle = "Укажи доп. факторы твоей жизни",
            onClick = { }
        )
    )

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
            DiaryProgressCard(progress = progress)
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
                completed = completed,
                total = 5
            )
        }

        items(inputItems) { item ->
            DailyInputCard(
                icon = item.icon,
                title = item.title,
                subtitle = item.subtitle,
                onClick = item.onClick
            )
        }

    }
}
