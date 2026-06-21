package com.example.imperfect.feature.feeling.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.icon.IconFilter
import com.example.imperfect.core.ui.designsystem.theme.BlueLightBackground
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.designsystem.theme.BlueShadowLight
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary
import com.example.imperfect.core.ui.kit.chips.FlexibleChip

@Composable
fun FeelingOptionChip(
    title: String,
    icon: IconFilter,
    selected: Boolean,
    onClick: () -> Unit
) {

    FlexibleChip(
        backgroundColor =
            if (selected)
                BlueShadowLight
            else
                BlueLightBackground,
        modifier = Modifier.clickable { onClick() }
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconFilterView(
                icon = icon,
                tint = if (selected) BluePrimary else TextSecondary
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = title,
                color = if (selected) BluePrimary else TextSecondary
            )
        }
    }
}