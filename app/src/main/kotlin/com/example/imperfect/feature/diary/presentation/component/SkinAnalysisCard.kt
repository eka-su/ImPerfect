package com.example.imperfect.feature.diary.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.imperfect.R
import com.example.imperfect.core.ui.designsystem.theme.TextTitle
import com.example.imperfect.core.ui.kit.button.PrimaryButton
import com.example.imperfect.core.ui.kit.container.SheetContainer
import com.example.imperfect.feature.diary.domain.model.DiaryPhotoPreview

@Composable
fun SkinAnalysisCard(
    percent: Int?,
    photos: List<DiaryPhotoPreview>,
    onClick: () -> Unit
) {

    val hasAnalysis = percent != null

    SheetContainer(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            // Заголовок + описание
            SkinAnalysisHeader()

            Spacer(Modifier.height(20.dp))

            // Полукргу с процентом + кружки фото
            SkinAnalysisContent(
                percent = percent,
                photos = photos
            )

            Spacer(Modifier.height(20.dp))

            /// Кнопка
            SkinAnalysisButton(
                hasAnalysis = hasAnalysis,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun SkinAnalysisHeader() {
    Column {

        Text(
            text = "Анализ кожи",
            style = MaterialTheme.typography.titleMedium,
            color = TextTitle
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = "Добавь фото для отслеживания динамики",
            style = MaterialTheme.typography.bodySmall,
            color = TextTitle
        )
    }
}


@Composable
private fun SkinAnalysisContent(
    percent: Int?,
    photos: List<DiaryPhotoPreview>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        SkinPercentIndicator(percent)

        PhotosTriangle(
            photos = photos,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun SkinAnalysisButton(
    hasAnalysis: Boolean,
    onClick: () -> Unit
) {
    PrimaryButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = if (hasAnalysis) "Подробнее" else "Сделать снимок"
            )

            Spacer(Modifier.width(8.dp))

            Icon(
                painter = painterResource(R.drawable.ic_arrow),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
