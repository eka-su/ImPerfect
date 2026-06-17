package com.example.imperfect.feature.skincare.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.icon.IconFilter
import com.example.imperfect.core.ui.designsystem.theme.Background
import com.example.imperfect.core.ui.kit.chips.IconChipItem
import com.example.imperfect.core.ui.kit.chips.ScrollIconSelector
import com.example.imperfect.core.ui.kit.list.BackTextRow
import com.example.imperfect.feature.skincare.domain.model.CareProduct
import com.example.imperfect.feature.skincare.domain.model.CareTime
import com.example.imperfect.feature.skincare.presentation.mapper.TimeSlotIconMapper
import com.example.imperfect.feature.skincare.presentation.model.RoutineRow
import com.example.imperfect.feature.skincare.presentation.state.SkincareUiState

@Composable
fun SkincareScreen(
    state: SkincareUiState,
    onBack: () -> Unit,
    onAddClick: () -> Unit,
    onSelectTime: (Int) -> Unit,
    onDelete: (Int, Int) -> Unit,
    onEdit: (CareProduct) -> Unit
) {

    val groupedRows = buildGroupedRows(state)
    val isEmptyAfterFilter = groupedRows.isEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(horizontal = 20.dp)
    ) {

        SkincareHeader(onBack = onBack)

        TimeSelector(
            state = state,
            onSelectTime = onSelectTime
        )

        Spacer(Modifier.height(16.dp))

        SkincareContent(
            isEmpty = isEmptyAfterFilter,
            groupedRows = groupedRows,
            onDelete = onDelete,
            onEdit = onEdit,
            onAddClick = onAddClick
        )
    }
}

@Composable
private fun SkincareHeader(
    onBack: () -> Unit
) {
    BackTextRow(
        text = "Уход за кожей",
        onClick = onBack,
        modifier = Modifier.padding(vertical = 16.dp)
    )
}

@Composable
private fun TimeSelector(
    state: SkincareUiState,
    onSelectTime: (Int) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        ScrollIconSelector(
            chipItems = buildTimeChips(state),
            selectedId = state.selectedTimeId,
            onSelect = onSelectTime
        )
    }
}

private fun buildTimeChips(state: SkincareUiState) =
    buildList {
        add(
            IconChipItem(
                id = 0,
                icon = IconFilter.Alltime,
                text = "Все"
            )
        )

        addAll(
            state.timeSlots.map {
                IconChipItem(
                    id = it.id,
                    icon = TimeSlotIconMapper.iconFor(it.code),
                    text = it.name
                )
            }
        )
    }

@Composable
private fun SkincareContent(
    isEmpty: Boolean,
    groupedRows: Map<CareTime, List<RoutineRow>>,
    onDelete: (Int, Int) -> Unit,
    onEdit: (CareProduct) -> Unit,
    onAddClick: () -> Unit
) {
    when {
        isEmpty -> {
            EmptySkincareState(
                onAddClick = onAddClick
            )
        }

        else -> {
            SkincareList(
                rows = groupedRows,
                onDelete = onDelete,
                onEdit = onEdit,
                onAddClick = onAddClick
            )
        }
    }
}

/* -------------------------- DATA MAPPING -------------------------- */

private fun buildGroupedRows(state: SkincareUiState) =
    state.routine
        .flatMap { routine ->
            routine.times
                .filter { time ->
                    state.selectedTimeId == 0 || time.id == state.selectedTimeId
                }
                .map { time ->
                    RoutineRow(routine.product, time)
                }
        }
        .groupBy { it.time }