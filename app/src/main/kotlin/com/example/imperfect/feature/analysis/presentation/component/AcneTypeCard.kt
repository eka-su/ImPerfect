package com.example.imperfect.feature.analysis.presentation.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.kit.chips.FlexibleChip
import com.example.imperfect.feature.analysis.presentation.model.AcneTypeUi

@Composable
fun AcneTypeCard(ui: AcneTypeUi) {

    FlexibleChip(
        backgroundColor = ui.fill,
        cornerRadius = 30.dp
    ) {

        IconAndText(ui)
    }
}

@Composable
private fun IconAndText(ui: AcneTypeUi) {

    Icon(
        painter = painterResource(id = ui.iconRes),
        contentDescription = null,
        tint = Color.Unspecified,
        modifier = Modifier.size(22.dp)
    )

    Spacer(Modifier.width(6.dp))

    Text(
        text = ui.name,
        color = ui.color,
        style = MaterialTheme.typography.titleSmall
    )

    ui.percent?.let { percent ->
        Spacer(Modifier.width(6.dp))

        Text(
            text = "$percent%",
            color = ui.color,
            style = MaterialTheme.typography.titleMedium
        )
    }
}