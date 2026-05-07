package com.example.imperfect.feature.photo.presentation.guide.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.kit.button.PrimaryButton

@Composable
fun PhotoGuideContent(
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Сделайте 3 фото лица:",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(Modifier.height(16.dp))

        Text("• Смотрите прямо")
        Text("• Поверните голову влево")
        Text("• Поверните голову вправо")

        Spacer(Modifier.height(32.dp))

        PrimaryButton(
            onClick = onStartClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Начать")
        }
    }
}
