package com.example.imperfect.core.ui.kit.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imperfect.R
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.designsystem.theme.ImPerfectTheme
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary
import com.example.imperfect.core.ui.designsystem.theme.TextTitle
import com.example.imperfect.core.ui.kit.dialog.primitives.AppAlertDialog

@Composable
fun ExitConfirmDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AppAlertDialog(
        onDismiss = onDismiss,

        title = {
            Text(
                text = stringResource(R.string.exit_dialog_title),
                color = TextTitle,
                style = MaterialTheme.typography.titleMedium
            )
        },

        text = {
            Column {
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string.exit_dialog_message),
                    color = TextSecondary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        },

        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(
                    text = stringResource(R.string.exit_dialog_confirm),
                    color = BluePrimary,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        },

        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(R.string.exit_dialog_dismiss),
                    color = BluePrimary,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ExitConfirmDialogPreview() {
    ImPerfectTheme {
        ExitConfirmDialog(
            onConfirm = {},
            onDismiss = {}
        )
    }
}

