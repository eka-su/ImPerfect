package com.example.imperfect.core.ui.kit.dialog.photosourcesheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.theme.TextTitle
import com.example.imperfect.core.ui.kit.icon.clickable.CloseButton

@Composable  // Заголовок с Close кнопкой
fun PhotoSheetHeader(
    title: String,
    onClose: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Text(
            text = title,
            color = TextTitle,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 5.dp) // маленький отступ слева для текста
        )
        CloseButton(
            onClick = onClose,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}
