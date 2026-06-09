package com.example.imperfect.feature.analysis.presentation.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.icon.IconAction
import com.example.imperfect.core.ui.designsystem.icon.IconSize
import com.example.imperfect.core.ui.designsystem.icon.IconStyles
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary
import com.example.imperfect.core.ui.kit.dialog.EditConfirmDialog
import com.example.imperfect.core.ui.kit.icon.primitives.IconActionButton
import com.example.imperfect.feature.analysis.domain.model.Detection
import com.example.imperfect.feature.photo.domain.model.Photo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnalysisPhotoPager(
    photos: List<Photo>,
    detectionsPerPhoto: List<List<Detection>>,
    onEditConfirmed: () -> Unit
) {

    val sortedPhotos = remember(photos) {
        photos.sortedBy { it.viewTypeId }
    }

    val pagerState = rememberPagerState {
        sortedPhotos.size
    }

    var showDetections by remember { mutableStateOf(true) }
    var showUI by remember { mutableStateOf(true) }
    var showEditDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(3f / 4f)
            .clip(RoundedCornerShape(22.dp))
    ) {

        PhotoPagerContent(
            photos = sortedPhotos,
            detectionsPerPhoto = detectionsPerPhoto,
            pagerState = pagerState,
            showDetections = showDetections,
            showUI = showUI
        )

        PhotoPagerEyeButton(
            showUI = showUI,
            onToggle = { showUI = !showUI }
        )

        if (showUI) {

            PhotoPagerTopControls(
                showDetections = showDetections,
                onToggleDetections = { showDetections = it },
                onEditClick = { showEditDialog = true }
            )

            PhotoPagerIndicators(
                pageCount = sortedPhotos.size,
                currentPage = pagerState.currentPage
            )
        }
    }

    if (showEditDialog) {
        EditConfirmDialog(
            onConfirm = {
                showEditDialog = false
                onEditConfirmed()
            },
            onDismiss = { showEditDialog = false }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun PhotoPagerContent(
    photos: List<Photo>,
    detectionsPerPhoto: List<List<Detection>>,
    pagerState: androidx.compose.foundation.pager.PagerState,
    showDetections: Boolean,
    showUI: Boolean
) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->

        AnalysisPhotoWithBoxes(
            photoPath = photos[page].filePath,
            detections = detectionsPerPhoto.getOrNull(page).orEmpty(),
            showDetections = showDetections && showUI
        )
    }
}

@Composable
private fun PhotoPagerEyeButton(
    showUI: Boolean,
    onToggle: () -> Unit
) {
    IconActionButton(
        action = if (showUI) IconAction.EyeClose else IconAction.EyeOpen,
        onClick = onToggle,
        modifier = Modifier
            .padding(16.dp),
        size = IconSize.SMALL,
        style = if (showUI) IconStyles.Input else IconStyles.InputPrimary
    )
}


@Composable
private fun BoxScope.PhotoPagerTopControls(
    showDetections: Boolean,
    onToggleDetections: (Boolean) -> Unit,
    onEditClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Switch(
            checked = showDetections,
            onCheckedChange = onToggleDetections
        )

        IconActionButton(
            action = IconAction.Edit,
            onClick = onEditClick,
            size = IconSize.SMALL,
            style = IconStyles.InputPrimary
        )
    }
}

@Composable
private fun BoxScope.PhotoPagerIndicators(
    pageCount: Int,
    currentPage: Int
) {

    Row(
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        repeat(pageCount) { index ->

            Box(
                modifier = Modifier
                    .size(width = 20.dp, height = 4.dp)
                    .clip(RoundedCornerShape(50))
                    .background(
                        if (currentPage == index)
                            BluePrimary
                        else
                            TextSecondary.copy(alpha = 0.35f)
                    )
            )
        }
    }
}