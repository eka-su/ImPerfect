package com.example.imperfect.feature.analysis.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.kit.container.InputContainer
import com.example.imperfect.core.ui.kit.container.SheetContainer

@Composable
fun ContextNoteSection(
    note: String,
    onNoteChange: (String) -> Unit
) {
    SheetContainer {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            ContextNoteHeader()

            Spacer(Modifier.height(12.dp))

            ContextNoteInput(
                note = note,
                onNoteChange = onNoteChange
            )
        }
    }
}

@Composable
private fun ContextNoteHeader() {

    Text(
        text = "Заметка о контексте анализа фото",
        style = MaterialTheme.typography.titleSmall
    )
}

@Composable
private fun ContextNoteInput(
    note: String,
    onNoteChange: (String) -> Unit
) {

    InputContainer(
        value = note,
        onValueChange = onNoteChange,
        placeholder = CONTEXT_NOTE_PLACEHOLDER
    )
}

private const val CONTEXT_NOTE_PLACEHOLDER =
    "Напиши условия, которые могли повлиять на результат анализа, например: после тренировки, после умывания, при ярком освещении и т.д."