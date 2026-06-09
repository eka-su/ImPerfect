package com.example.imperfect.feature.analysis.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary
import com.example.imperfect.core.ui.designsystem.theme.TextTitle
import com.example.imperfect.core.ui.kit.button.PrimaryButton
import com.example.imperfect.core.ui.kit.container.SheetContainer

@Composable
fun AnalysisPhotoInfoCard(
    scanTime: String,
    onOpenClick: () -> Unit
) {

    SheetContainer(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            InfoBlock(scanTime)

            Spacer(Modifier.width(12.dp))

            ActionBlock(onOpenClick)
        }
    }
}

@Composable
private fun RowScope.InfoBlock(scanTime: String) {

    Column(
        modifier = Modifier.weight(1f)
    ) {

        Text(
            text = "Дата сканирования",
            style = MaterialTheme.typography.titleMedium,
            color = TextTitle
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = scanTime,
            style = MaterialTheme.typography.labelMedium,
            color = TextSecondary
        )
    }
}

@Composable
private fun ActionBlock(onOpenClick: () -> Unit) {

    PrimaryButton(onClick = onOpenClick) {
        Text("Сравнить")
    }
}