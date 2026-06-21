package com.example.imperfect.feature.feeling.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.navigation.Router
import com.example.imperfect.core.ui.kit.button.PrimaryButton
import com.example.imperfect.core.ui.kit.list.BackTextRow
import com.example.imperfect.feature.feeling.presentation.component.FeelingNoteSection
import com.example.imperfect.feature.feeling.presentation.component.FeelingOptionsFlow
import com.example.imperfect.feature.feeling.presentation.viewmodel.FeelingViewModel

@Composable
fun FeelingScreen(
    diaryId: Int,
    screenCode: String,
    router: Router,
    viewModel: FeelingViewModel,
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        viewModel.load(diaryId, screenCode)
    }

    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 20.dp, end = 20.dp)
            .pointerInput(Unit) {
                detectTapGestures {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }
            }
    ) {

        BackTextRow(
            text = state.title, // Ощущение кожи /Здоровье и чувства
            onClick = { router.back()}
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Состояние",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(12.dp))

        FeelingOptionsFlow(
            options = state.options,
            onToggle = viewModel::toggleOption
        )

        Spacer(Modifier.height(24.dp))

        FeelingNoteSection(
            value = state.note,
            onValueChange = viewModel::updateNote
        )

        Spacer(Modifier.height(24.dp))

        AnimatedVisibility(
            visible = state.hasChanges
        ) {
            PrimaryButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.save(diaryId)
                }
            ) {
                Text("Сохранить")
            }
        }
    }
}