package com.example.imperfect.feature.skincare.presentation.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.imperfect.core.ui.designsystem.icon.IconAction
import com.example.imperfect.core.ui.designsystem.theme.Background
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.designsystem.theme.IconGray
import com.example.imperfect.core.ui.designsystem.theme.InputBackground
import com.example.imperfect.core.ui.designsystem.theme.Red
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary
import com.example.imperfect.core.ui.designsystem.theme.TextTitle
import com.example.imperfect.core.ui.kit.button.PrimaryButton
import com.example.imperfect.core.ui.kit.chips.FlexibleChip
import com.example.imperfect.core.ui.kit.chips.IconChipItem
import com.example.imperfect.core.ui.kit.chips.ScrollIconSelector
import com.example.imperfect.core.ui.kit.list.BackTextRow
import com.example.imperfect.core.ui.kit.list.LearnMoreText
import com.example.imperfect.feature.skincare.domain.model.CareProduct
import com.example.imperfect.feature.skincare.presentation.mapper.CategoryIconMapper
import com.example.imperfect.feature.skincare.presentation.mapper.TimeSlotIconMapper
import com.example.imperfect.feature.skincare.presentation.state.SkincareUiState
import com.example.imperfect.feature.skincare.presentation.viewmodel.SkincareViewModel

@Composable
fun ProductDetailScreen(
    product: CareProduct,
    onBack: () -> Unit,
    onToggleFavorite: (CareProduct) -> Unit,
    onEdit: () -> Unit,
    state: SkincareUiState,
    onSelectTime: (Int) -> Unit,
    viewModel: SkincareViewModel,
    diaryId: Int,
) {
    var isFavorite by remember { mutableStateOf(product.isFavorite) }
    var expanded by remember { mutableStateOf(false) }
    var canExpand by remember { mutableStateOf(false) }

    val selectedTimeId = state.selectedTimeId
    val isEnabled = state.routine.any { routine ->
        routine.product.id == product.id &&
                routine.times.any { it.id == selectedTimeId }
    }

    Log.d("ProductDetailScreen", "OPEN productId=${product.id}, diaryId=$diaryId")
    Log.d("ProductDetailScreen", " selectedTimeId=${state.selectedTimeId}")

    ClearFocusOnTap {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {

            Header(onBack)

            TimeSelector(state, onSelectTime)

            Spacer(Modifier.height(8.dp))

            ProductImageSection(
                product = product,
                isFavorite = isFavorite,
                onFavoriteClick = {
                    Log.d(
                        "ProductDetailScreen",
                        "FAVORITE clicked: productId=${product.id}, isFavorite(before)=$isFavorite"
                    )
                    isFavorite = !isFavorite
                    onToggleFavorite(product)
                }
            )

            Spacer(Modifier.height(20.dp))

            ProductNameWithCategory(product)

            Spacer(Modifier.height(4.dp))

            ProductBrand(product)

            Spacer(Modifier.height(8.dp))

            ProductDescription(
                product = product,
                expanded = expanded,
                canExpand = canExpand,
                onExpandChanged = { expanded = it },
                onCanExpandChanged = { canExpand = it }
            )

            Spacer(Modifier.height(16.dp))

            ProductCreatedAt(product)

            Spacer(Modifier.height(16.dp))

            RoutineSwitch(
                isEnabled = isEnabled,
                onCheckedChange = {
                    selectedTimeId?.let {
                        viewModel.toggleRoutineForProduct(
                            product = product,
                            diaryId = diaryId,
                            timeId = it
                        )
                    }
                }
            )

            Spacer(Modifier.height(24.dp))

            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    Log.d("ProductDetailScreen", "EDIT clicked productId=${product.id}")
                    onEdit()
                }
            ) {
                Text("Изменить")
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun Header(onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BackTextRow(
            text = "Просмотр продукта",
            onClick = onBack
        )
    }
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
            chipItems = state.timeSlots.map { slot ->
                IconChipItem(
                    id = slot.id,
                    icon = TimeSlotIconMapper.iconFor(slot.code),
                    text = slot.name
                )
            },
            selectedId = state.selectedTimeId,
            onSelect = onSelectTime
        )
    }
}

@Composable
private fun ProductImageSection(
    product: CareProduct,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(227.dp)
    ) {
        if (product.imagePath != null) {
            AsyncImage(
                model = product.imagePath,
                contentDescription = null,
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(20.dp))
                    .background(InputBackground),
                contentScale = ContentScale.Crop
            )
        } else {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(20.dp))
                    .background(InputBackground),
                contentAlignment = Alignment.Center
            ) {
                Text("Нет фото", color = TextSecondary)
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-18).dp, y = 18.dp)
                .size(45.dp)
                .background(Color.White, RoundedCornerShape(60))
                .clickable { onFavoriteClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(
                    id = if (isFavorite)
                        IconAction.FavoriteFill.iconRes
                    else
                        IconAction.Favorite.iconRes
                ),
                contentDescription = null,
                tint = if (isFavorite) Red else IconGray,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
private fun ProductBrand(product: CareProduct) {
    Text(
        text = product.brand?.takeIf { it.isNotBlank() }
            ?.let { "Бренд - $it" }
            ?: "Бренд отсутствует",
        style = MaterialTheme.typography.displaySmall,
        color = TextSecondary
    )
}

@Composable
private fun ProductDescription(
    product: CareProduct,
    expanded: Boolean,
    canExpand: Boolean,
    onExpandChanged: (Boolean) -> Unit,
    onCanExpandChanged: (Boolean) -> Unit
) {
    val description = product.description?.takeIf { it.isNotBlank() }
        ?: "Описание отсутствует"

    Text(
        text = description,
        style = MaterialTheme.typography.bodyMedium,
        color = TextTitle,
        maxLines = if (expanded) Int.MAX_VALUE else 3,
        onTextLayout = { result ->
            if (!expanded) {
                onCanExpandChanged(result.hasVisualOverflow)
            }
        }
    )

    if (canExpand) {
        Spacer(Modifier.height(6.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            LearnMoreText(
                text = if (expanded) "Скрыть" else "Раскрыть",
                onClick = { onExpandChanged(!expanded) }
            )
        }
    }
}

@Composable
private fun ProductCreatedAt(product: CareProduct) {
    Text(
        text = "Дата создания",
        style = MaterialTheme.typography.titleSmall,
        color = TextTitle
    )

    Spacer(Modifier.height(8.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(InputBackground, RoundedCornerShape(16.dp))
            .padding(14.dp)
    ) {
        Text(
            text = product.createdAt ?: "",
            color = TextSecondary
        )
    }
}

@Composable
private fun RoutineSwitch(
    isEnabled: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Включено",
            style = MaterialTheme.typography.titleSmall,
            color = TextTitle
        )

        Switch(
            checked = isEnabled,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
fun ProductNameWithCategory(product: CareProduct) {
    val icon = CategoryIconMapper.iconFor(
        product.category?.code ?: "OTHER"
    )

    val name = product.name
    val isLong = name.length > 28

    if (isLong) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Text(
                text = product.name,
                style = MaterialTheme.typography.titleLarge,
                color = TextTitle,
                softWrap = true
            )

            Spacer(Modifier.height(6.dp))

            FlexibleChip {
                Icon(
                    painter = painterResource(icon.iconRes),
                    contentDescription = null,
                    tint = BluePrimary,
                    modifier = Modifier.size(14.dp)
                )

                Spacer(Modifier.width(6.dp))

                Text(
                    text = product.category?.name ?: "Категория",
                    color = BluePrimary
                )
            }
        }
    } else {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                color = TextTitle,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )

            FlexibleChip {
                Icon(
                    painter = painterResource(icon.iconRes),
                    contentDescription = null,
                    tint = BluePrimary,
                    modifier = Modifier.size(14.dp)
                )

                Spacer(Modifier.width(6.dp))

                Text(
                    text = product.category?.name ?: "Категория",
                    color = BluePrimary
                )
            }
        }
    }
}