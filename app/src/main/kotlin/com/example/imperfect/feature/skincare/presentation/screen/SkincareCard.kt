package com.example.imperfect.feature.skincare.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.imperfect.core.ui.designsystem.icon.IconSize
import com.example.imperfect.core.ui.designsystem.icon.IconStyles
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.designsystem.theme.TextTertiary
import com.example.imperfect.core.ui.kit.chips.FlexibleChip
import com.example.imperfect.core.ui.kit.container.SheetContainer
import com.example.imperfect.core.ui.kit.icon.clickable.DeleteButton
import com.example.imperfect.core.ui.kit.icon.clickable.EditButton
import com.example.imperfect.core.ui.kit.icon.primitives.IconContainer
import com.example.imperfect.feature.skincare.domain.model.CareProduct
import com.example.imperfect.feature.skincare.presentation.mapper.CategoryIconMapper
@Composable
fun SkincareCard(
    product: CareProduct,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    showActions: Boolean = true
) {
    ClearFocusOnTap {
        SheetContainer {
            SkincareCardContent(
                product = product,
                onEdit = onEdit,
                onDelete = onDelete,
                showActions = showActions
            )
        }
    }
}

@Composable
private fun SkincareCardContent(
    product: CareProduct,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    showActions: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SkincareCardImage(product)

        Spacer(Modifier.width(16.dp))

        SkincareCardInfo(product)

        SkincareCardActions(
            onEdit = onEdit,
            onDelete = onDelete,
            showActions = showActions
        )
    }
}

@Composable
private fun SkincareCardImage(product: CareProduct) {
    if (product.imagePath != null) {
        AsyncImage(
            model = product.imagePath,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(47.dp)
                .clip(RoundedCornerShape(12.dp))
        )
    } else {
        IconContainer(
            action = CategoryIconMapper.iconFor(
                product.category?.code ?: "OTHER"
            ),
            size = IconSize.LARGE,
            style = IconStyles.Filled
        )
    }
}

@Composable
private fun RowScope.SkincareCardInfo(product: CareProduct) {

    fun shorten(name: String, max: Int = 26): String {
        return if (name.length <= max) name else name.take(max) + "..."
    }

    Column(
        modifier = Modifier.weight(1f)
    ) {
        SkincareCardCategory(product)

        Spacer(Modifier.height(2.dp))

        Text(
            text = shorten(product.name, 26),
            style = MaterialTheme.typography.displaySmall,
            color = TextTertiary,
            modifier = Modifier.padding(start = 3.dp)
        )
    }
}

@Composable
private fun SkincareCardCategory(product: CareProduct) {

    val categoryIcon = CategoryIconMapper.iconFor(
        product.category?.code ?: "OTHER"
    )

    FlexibleChip {

        Icon(
            painter = painterResource(categoryIcon.iconRes),
            contentDescription = null,
            modifier = Modifier.size(14.dp),
            tint = BluePrimary
        )

        Spacer(Modifier.width(6.dp))

        Text(
            text = product.category?.name ?: "Категория",
            style = MaterialTheme.typography.bodySmall,
            color = BluePrimary
        )
    }
}

@Composable
private fun SkincareCardActions(
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    showActions: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        EditButton(onClick = onEdit)

        Spacer(Modifier.width(8.dp))

        if (showActions) {
            DeleteButton(onClick = onDelete)
        }
    }
}