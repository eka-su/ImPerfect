package com.example.imperfect.core.ui.kit.dialog.photosourcesheet

import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.imperfect.R
import com.example.imperfect.core.ui.designsystem.theme.Background
import com.example.imperfect.core.ui.designsystem.theme.ImPerfectTheme
import com.example.imperfect.core.ui.kit.icon.notclickable.CameraContainer
import com.example.imperfect.core.ui.kit.icon.notclickable.GalleryContainer

@Composable
fun PhotoSourceSheet(
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit,
    onClose: () -> Unit
) {
    Dialog(onDismissRequest = onClose) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Background,
                    shape = RoundedCornerShape(22.dp)
                )
                .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                PhotoSheetHeader(
                    title = stringResource(R.string.photo_sheet_title),
                    onClose = onClose
                )

                PhotoSheetRow(
                    text = stringResource(R.string.photo_sheet_camera),
                    icon = { CameraContainer() },
                    onClick = onCameraClick
                )

                PhotoSheetRow(
                    text = stringResource(R.string.photo_sheet_gallery),
                    icon = { GalleryContainer() },
                    onClick = onGalleryClick
                )
            }
        }
    }
}

@Preview(
    name = "Photo Source Sheet - Light",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun PhotoSourceSheetPreview() {
    ImPerfectTheme {
        PhotoSourceSheet(
            onCameraClick = {  },
            onGalleryClick = {  },
            onClose = {  }
        )
    }
}