package com.example.imperfect.feature.feeling.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.imperfect.feature.feeling.domain.model.FeelingOption
import com.example.imperfect.feature.feeling.presentation.utils.FeelingIconMapper
import kotlin.collections.forEach

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FeelingOptionsFlow(
    options: List<FeelingOption>,
    onToggle: (Int) -> Unit
) {

    FlowRow(
        horizontalArrangement =
            Arrangement.spacedBy(8.dp),
        verticalArrangement =
            Arrangement.spacedBy(8.dp)
    ) {

        options.forEach { option ->

            FeelingOptionChip(
                title = option.name,
                icon = FeelingIconMapper.getIcon(option.name),
                selected = option.selected,
                onClick = {
                    onToggle(option.id)
                }
            )
        }
    }
}