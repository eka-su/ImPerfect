package com.example.imperfect.feature.skincare.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.icon.IconAction
import com.example.imperfect.core.ui.designsystem.icon.IconSize
import com.example.imperfect.core.ui.designsystem.icon.IconStyles.Primary
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.designsystem.theme.IconGray
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary
import com.example.imperfect.core.ui.designsystem.theme.TextTitle
import com.example.imperfect.core.ui.kit.container.InputContainer
import com.example.imperfect.core.ui.kit.icon.primitives.IconContainer
import com.example.imperfect.core.ui.kit.list.BackTextRow
import com.example.imperfect.core.ui.kit.list.LearnMoreText
import com.example.imperfect.feature.skincare.domain.model.CareProduct
import com.example.imperfect.feature.skincare.presentation.state.SkincareUiState
import kotlinx.coroutines.delay

@Composable
fun AddProductScreen(
    state: SkincareUiState,
    onBack: () -> Unit,
    onSearch: (String) -> Unit,
    onCreateProduct: () -> Unit,
    onDelete: (Int) -> Unit,
    onEdit: (CareProduct) -> Unit,
    openProductsBase: () -> Unit,
    onOpenFavorites: () -> Unit,
) {
    var query by remember { mutableStateOf("") }
    var isFavoritesExpanded by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
        ) {

            HeaderSection(
                onBack = onBack,
                openProductsBase = openProductsBase
            )

            Spacer(Modifier.height(12.dp))

            SearchSection(
                query = query,
                onQueryChange = { query = it },
                onSearch = onSearch
            )

            Spacer(Modifier.height(8.dp))

            SearchResultsSection(
                query = query,
                state = state,
                onDelete = onDelete,
                onEdit = onEdit
            )

            Spacer(Modifier.height(24.dp))

            RecentSection(
                state = state,
                onDelete = onDelete,
                onEdit = onEdit
            )

            Spacer(Modifier.height(12.dp))

            FavoritesSection(
                state = state,
                isExpanded = isFavoritesExpanded,
                onToggleExpanded = { isFavoritesExpanded = !isFavoritesExpanded },
                onDelete = onDelete,
                onEdit = onEdit,
                onOpenFavorites = onOpenFavorites
            )

            Spacer(Modifier.height(24.dp))

            CreateProductSection(
                onCreateProduct = onCreateProduct
            )

            Spacer(Modifier.height(80.dp))
        }
    }
}

@Composable
private fun HeaderSection(
    onBack: () -> Unit,
    openProductsBase: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackTextRow(
            text = "Добавить продукт",
            onClick = onBack
        )

        Icon(
            painter = painterResource(id = IconAction.ShowAll.iconRes),
            contentDescription = IconAction.ShowAll.contentDescription,
            tint = IconGray,
            modifier = Modifier
                .size(24.dp)
                .clickable { openProductsBase() }
        )
    }
}

@Composable
private fun SearchSection(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit
) {
    InputContainer(
        value = query,
        onValueChange = onQueryChange,
        placeholder = "Введите название продукта",
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                painter = painterResource(IconAction.Search.iconRes),
                contentDescription = null,
                tint = IconGray,
                modifier = Modifier.size(18.dp)
            )
        }
    )

    LaunchedEffect(query) {
        delay(300)
        onSearch(query)
    }
}

@Composable
private fun SearchResultsSection(
    query: String,
    state: SkincareUiState,
    onDelete: (Int) -> Unit,
    onEdit: (CareProduct) -> Unit
) {
    if (query.isBlank()) return

    if (state.searchResults.isEmpty()) {
        Text(
            text = "Ничего не найдено",
            color = TextSecondary
        )
    } else {
        state.searchResults.forEach { product ->
            Spacer(Modifier.height(8.dp))
            SkincareCard(
                product = product,
                onDelete = { onDelete(product.id) },
                onEdit = { onEdit(product) },
                showActions = false
            )
        }
    }
}

@Composable
private fun RecentSection(
    state: SkincareUiState,
    onDelete: (Int) -> Unit,
    onEdit: (CareProduct) -> Unit
) {
    Text(
        text = "Недавно добавленные",
        style = MaterialTheme.typography.titleMedium,
        color = TextTitle
    )

    Spacer(Modifier.height(8.dp))

    if (state.recent.isNotEmpty()) {
        state.recent.take(2).forEach { product ->
            Spacer(Modifier.height(8.dp))
            SkincareCard(
                product = product,
                onDelete = { onDelete(product.id) },
                onEdit = { onEdit(product) },
                showActions = false
            )
        }
    } else {
        EmptyPlaceholder(text = "Пока ничего нет")
    }
}

@Composable
private fun FavoritesSection(
    state: SkincareUiState,
    isExpanded: Boolean,
    onToggleExpanded: () -> Unit,
    onDelete: (Int) -> Unit,
    onEdit: (CareProduct) -> Unit,
    onOpenFavorites: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Избранное",
            style = MaterialTheme.typography.titleMedium,
            color = TextTitle
        )

        Icon(
            painter = painterResource(id = IconAction.ShowAll.iconRes),
            contentDescription = IconAction.ShowAll.contentDescription,
            tint = IconGray,
            modifier = Modifier
                .size(24.dp)
                .clickable { onOpenFavorites() }
        )
    }

    Spacer(Modifier.height(8.dp))

    val favoritesToShow =
        if (isExpanded) state.favorites
        else state.favorites.take(2)

    if (state.favorites.isNotEmpty()) {
        favoritesToShow.forEach { product ->
            Spacer(Modifier.height(8.dp))
            SkincareCard(
                product = product,
                onDelete = { onDelete(product.id) },
                onEdit = { onEdit(product) },
                showActions = false
            )
        }
    } else {
        EmptyPlaceholder(text = "Пока ничего нет")
    }

    if (state.favorites.size > 2) {
        Spacer(Modifier.height(14.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            LearnMoreText(
                text = if (isExpanded) "Скрыть" else "Показать все",
                onClick = onToggleExpanded
            )
        }
    }
}

@Composable
private fun CreateProductSection(
    onCreateProduct: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Text(
            text = "Не нашли то, что искали?",
            style = MaterialTheme.typography.bodyLarge,
            color = TextTitle
        )

        Spacer(Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .clickable { onCreateProduct() }
                .fillMaxWidth()
                .height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconContainer(
                action = IconAction.Add,
                size = IconSize.LARGE,
                style = Primary
            )

            Spacer(Modifier.width(12.dp))

            Text(
                text = "Введите напрямую",
                style = MaterialTheme.typography.labelSmall,
                color = BluePrimary
            )
        }
    }
}

@Composable
private fun EmptyPlaceholder(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary
        )
    }
}