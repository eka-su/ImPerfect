package com.example.imperfect.feature.diary.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.imperfect.R
import com.example.imperfect.core.ui.designsystem.theme.BlueLightBackground
import com.example.imperfect.core.ui.designsystem.theme.TextPrimary
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary
import com.example.imperfect.feature.diary.domain.model.DiaryPhotoPreview

@Composable
fun PhotosTriangle(
    photos: List<DiaryPhotoPreview>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            // Как треугольник два и справа 1 посредине

            PhotosTriangleSide(
                left = photos.getOrNull(1),
                right = photos.getOrNull(2)
            )

            Spacer(Modifier.width(26.dp))

            PhotoItemBlock(
                photo = photos.getOrNull(0),
                title = "Спереди",
                icon = painterResource(R.drawable.ic_front)
            )
        }
    }
}

@Composable
private fun PhotosTriangleSide(
    left: DiaryPhotoPreview?,
    right: DiaryPhotoPreview?
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        PhotoItemBlock(
            photo = left,
            title = "Слева",
            icon = painterResource(R.drawable.ic_left)
        )

        PhotoItemBlock(
            photo = right,
            title = "Справа",
            icon = painterResource(R.drawable.ic_right)
        )
    }
}

@Composable
private fun PhotoItemBlock(
    photo: DiaryPhotoPreview?,
    title: String,
    icon: Painter
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (photo != null) {
            AsyncImage(
                model = photo.filePath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
        } else {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(BlueLightBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = TextPrimary,
                    modifier = Modifier.size(36.dp)
                )
            }
        }

        Spacer(Modifier.height(4.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.displaySmall,
            color = TextSecondary
        )
    }
}
