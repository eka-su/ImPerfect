package com.example.imperfect.core.ui.kit.dialog.primitives

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.theme.Background

@Composable
fun AppAlertDialog(
    onDismiss: () -> Unit,
    title: @Composable () -> Unit,
    text: @Composable () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Background,
        shape = RoundedCornerShape(20.dp),
        title = title,
        text = text,
        confirmButton = confirmButton,
        dismissButton = dismissButton
    )
}
