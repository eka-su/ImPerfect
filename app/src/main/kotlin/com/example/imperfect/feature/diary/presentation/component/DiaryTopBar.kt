package com.example.imperfect.feature.diary.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.icon.IconAction
import com.example.imperfect.core.ui.designsystem.icon.IconSize
import com.example.imperfect.core.ui.designsystem.icon.IconStyles
import com.example.imperfect.core.ui.designsystem.theme.TextPrimary
import com.example.imperfect.core.ui.kit.icon.primitives.IconActionButton

@Composable
fun DiaryTopBar(
    onCurrentDayClick: () -> Unit,
    onGalleryClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        DiaryTopBarTitle(
            modifier = Modifier.weight(1f)
        )

        // Кнопки справа
        DiaryTopBarActions(
            onCurrentDayClick = onCurrentDayClick,
            onGalleryClick = onGalleryClick
        )
    }
}

@Composable
private fun DiaryTopBarTitle(
    modifier: Modifier = Modifier
) {
    Text(
        text = "Дневник кожи",
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge,
        color = TextPrimary
    )
}

@Composable
private fun DiaryTopBarActions(
    onCurrentDayClick: () -> Unit,
    onGalleryClick: () -> Unit
) {
    IconActionButton(
        action = IconAction.Location,
        onClick = onCurrentDayClick,
        size = IconSize.SMALL,
        style = IconStyles.Default
    )

    Spacer(Modifier.width(8.dp))

    IconActionButton(
        action = IconAction.Folder,
        onClick = onGalleryClick,
        size = IconSize.SMALL,
        style = IconStyles.Default
    )
}