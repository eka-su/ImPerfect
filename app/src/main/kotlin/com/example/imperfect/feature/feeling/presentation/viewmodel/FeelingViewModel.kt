package com.example.imperfect.feature.feeling.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imperfect.feature.feeling.domain.usecase.GetFeelingFormUseCase
import com.example.imperfect.feature.feeling.domain.usecase.SaveFeelingFormUseCase
import com.example.imperfect.feature.feeling.presentation.state.FeelingUiState
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class FeelingViewModel(
    private val getFeelingFormUseCase: GetFeelingFormUseCase,
    private val saveFeelingFormUseCase: SaveFeelingFormUseCase
) : ViewModel() {

    var state by mutableStateOf(
        FeelingUiState()
    )
        private set

    fun load(
        diaryId: Int,
        code: String
    ) {
        viewModelScope.launch {

            Log.d("FeelingVM", "load started")
            Log.d("FeelingVM", "diaryId=$diaryId code=$code")

            val form = getFeelingFormUseCase(
                diaryId,
                code
            )

            Log.d("FeelingVM", "form received")
            Log.d("FeelingVM", "screenId=${form.screenId}")
            Log.d("FeelingVM", "title=${form.title}")
            Log.d("FeelingVM", "note=${form.note}")
            Log.d("FeelingVM", "options count=${form.options.size}")
            Log.d("FeelingVM", "options=${form.options}")

            state = FeelingUiState(
                screenId = form.screenId,
                title = form.title,
                note = form.note,
                options = form.options,
                initialSelectedIds =
                    form.options
                        .filter { it.selected }
                        .map { it.id }
                        .toSet(),
                initialNote = form.note
            )

            Log.d("FeelingVM", "state updated")
            Log.d("FeelingVM", "state options count=${state.options.size}")
        }
    }
    fun toggleOption(id: Int) {

        state = state.copy(
            options = state.options.map {

                if (it.id == id) {
                    it.copy(
                        selected = !it.selected
                    )
                } else {
                    it
                }
            }
        )
    }

    fun updateNote(value: String) {

        state = state.copy(
            note = value
        )
    }

    fun save(
        diaryId: Int
    ) {

        viewModelScope.launch {

            saveFeelingFormUseCase(
                diaryId = diaryId,
                screenId = state.screenId,
                selectedIds = state.selectedIds,
                note = state.note
            )

            state = state.copy(
                initialSelectedIds = state.selectedIds,
                initialNote = state.note
            )
        }
    }
}