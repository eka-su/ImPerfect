package com.example.imperfect.feature.skincare.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.theme.Background
import com.example.imperfect.core.ui.kit.icon.clickable.FilterButton
import com.example.imperfect.core.ui.kit.list.BackTextRow
import com.example.imperfect.feature.skincare.domain.model.CareProduct

@Composable
fun FavoritesScreen(
    favorites: List<CareProduct>,
    onBack: () -> Unit,
    onDelete: (Int) -> Unit,
    onEdit: (CareProduct) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(horizontal = 20.dp)
    ) {

        FavoritesHeader(
            onBack = onBack
        )

        FavoritesList(
            favorites = favorites,
            onDelete = onDelete,
            onEdit = onEdit
        )
    }
}

@Composable
private fun FavoritesHeader(
    onBack: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        BackTextRow(
            text = "Избранное",
            onClick = onBack
        )

        Spacer(modifier = Modifier.weight(1f))

        FilterButton(
            onClick = { }
        )
    }
}

@Composable
private fun FavoritesList(
    favorites: List<CareProduct>,
    onDelete: (Int) -> Unit,
    onEdit: (CareProduct) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(favorites) { product ->
            FavoriteItem(
                product = product,
                onDelete = onDelete,
                onEdit = onEdit
            )
        }
    }
}

@Composable
private fun FavoriteItem(
    product: CareProduct,
    onDelete: (Int) -> Unit,
    onEdit: (CareProduct) -> Unit
) {
    SkincareCard(
        product = product,
        onDelete = { onDelete(product.id) },
        onEdit = { onEdit(product) },
        showActions = false
    )
}