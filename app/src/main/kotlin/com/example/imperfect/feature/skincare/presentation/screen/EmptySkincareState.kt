package com.example.imperfect.feature.skincare.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary
import com.example.imperfect.core.ui.kit.button.PrimaryButton

@Composable
fun EmptySkincareState(
    onAddClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        EmptyText()

        Spacer(Modifier.height(12.dp))

        AddButton(onAddClick)
    }
}

@Composable
private fun EmptyText() {
    Text(
        text = "Пока ничего не добавлено",
        style = MaterialTheme.typography.bodyLarge,
        color = TextSecondary
    )
}

@Composable
private fun AddButton(onAddClick: () -> Unit) {
    PrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = onAddClick
    ) {
        Text("Добавить")
    }
}