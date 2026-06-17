package com.example.imperfect.feature.skincare.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.kit.button.PrimaryButton
import com.example.imperfect.feature.skincare.domain.model.CareProduct
import com.example.imperfect.feature.skincare.domain.model.CareTime
import com.example.imperfect.feature.skincare.presentation.model.RoutineRow

@Composable
fun SkincareList(
    rows: Map<CareTime, List<RoutineRow>>,
    onDelete: (Int, Int) -> Unit,
    onEdit: (CareProduct) -> Unit,
    onAddClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        skincareRowsSection(
            rows = rows,
            onDelete = onDelete,
            onEdit = onEdit
        )

        addButtonSection(onAddClick)
    }
}

private fun LazyListScope.skincareRowsSection(
    rows: Map<CareTime, List<RoutineRow>>,
    onDelete: (Int, Int) -> Unit,
    onEdit: (CareProduct) -> Unit
) {
    rows.forEach { (time, items) ->

        item {
            Text(
                text = time.name,
                style = MaterialTheme.typography.titleMedium
            )
        }

        items(items) { row ->
            SkincareCard(
                product = row.product,
                onDelete = {
                    onDelete(row.product.id, row.time.id)
                },
                onEdit = { onEdit(row.product) }
            )
        }
    }
}

private fun LazyListScope.addButtonSection(
    onAddClick: () -> Unit
) {
    item {
        Spacer(Modifier.height(16.dp))

        PrimaryButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onAddClick
        ) {
            Text("Добавить")
        }
    }
}