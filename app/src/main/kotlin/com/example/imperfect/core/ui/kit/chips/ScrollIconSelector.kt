package com.example.imperfect.core.ui.kit.chips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.icon.IconFilter
import com.example.imperfect.core.ui.designsystem.theme.BlueLightBackground
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.designsystem.theme.IconGray
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun ScrollIconSelector(
    chipItems: List<IconChipItem>,
    selectedId: Int?,
    onSelect: (Int) -> Unit
) {
    LazyRow(
    ) {
        items(chipItems) { item ->

            val selected = item.id == selectedId

            val bgColor = if (selected) {
                BlueLightBackground
            } else {
                Color.Transparent
            }

            val contentColor = if (selected) {
                BluePrimary
            } else {
                IconGray
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(bgColor)
                    .clickable { onSelect(item.id) }
                    .padding(
                        horizontal = 10.dp,
                        vertical = 10.dp
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {

                    // текст сначала
                    Text(
                        text = item.text,
                        color = contentColor,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    // иконка после текста
                    Icon(
                        painter = painterResource(item.icon.iconRes),
                        contentDescription = item.icon.contentDescription,
                        tint = contentColor,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun ScrollIconSelectorPreview() {

    var selectedFilter by remember {
        mutableStateOf(1)
    }

    val analysisFilters = listOf(
        IconChipItem(1, IconFilter.AllZones, "Все"),
        IconChipItem(2, IconFilter.Left2, "Слева"),
        IconChipItem(3, IconFilter.Front2, "Спереди"),
        IconChipItem(4, IconFilter.Right2, "Справа")
    )

    ScrollIconSelector(
        chipItems = analysisFilters,
        selectedId = selectedFilter,
        onSelect = { selectedFilter = it }
    )
}