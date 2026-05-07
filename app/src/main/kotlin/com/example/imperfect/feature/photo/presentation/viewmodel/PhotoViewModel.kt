package com.example.imperfect.feature.photo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imperfect.core.utils.UnsavedChangesController
import com.example.imperfect.feature.photo.domain.model.Photo
import com.example.imperfect.feature.photo.domain.usecase.AddPhotoUseCase
import com.example.imperfect.feature.photo.domain.usecase.ValidatePhotosUseCase
import com.example.imperfect.feature.photo.domain.util.replaceByStep
import com.example.imperfect.feature.photo.presentation.event.PhotoFlowEvent
import com.example.imperfect.feature.photo.presentation.state.FlowStage
import com.example.imperfect.feature.photo.presentation.state.PhotoFlowState
import com.example.imperfect.feature.photo.presentation.state.Source
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PhotoViewModel(
    private val addPhotoUseCase: AddPhotoUseCase,
    private val validatePhotosUseCase: ValidatePhotosUseCase
): ViewModel() {

    // STATE
    private val _state = MutableStateFlow(PhotoFlowState())
    val state: StateFlow<PhotoFlowState> = _state

    private val _event = MutableStateFlow<PhotoFlowEvent?>(null)
    val event: StateFlow<PhotoFlowEvent?> = _event

    // FLOW CONTROL
    fun start(source: Source) {
        _state.value = PhotoFlowState(
            source = source,
            stage = FlowStage.GUIDE,
            step = 1,
            photos = emptyList()
        )
    }

    fun setStage(stage: FlowStage) {
        _state.update { it.copy(stage = stage) }
    }

    fun nextStep() {
        _state.update { it.copy(step = it.step + 1) }
    }

    fun canFinish(): Boolean {
        return validatePhotosUseCase(_state.value.photos)
    }

    fun addPhoto(path: String) {
        val step = _state.value.step

        val photo = Photo(
            diaryId = 1,
            filePath = path,
            viewTypeId = step,
            createdAt = System.currentTimeMillis().toString()
        )

        _state.update { state ->
            state.copy(
                photos = state.photos.replaceByStep(photo),
                currentPhoto = photo
            )
        }

        UnsavedChangesController.markChanged()
    }

    // PREVIEW / UI LOGIC
    fun onPreviewBackClicked() {
        if (_state.value.photos.isNotEmpty()) {
            _state.update { it.copy(showExitDialog = true) }
        } else {
            setStage(FlowStage.GUIDE)
        }
    }

    fun dismissExitDialog() {
        _state.update { it.copy(showExitDialog = false) }
    }

    fun confirmExit() {
        reset()
        _state.update { it.copy(showExitDialog = false) }
    }

    // SAVE / PERSISTENCE
    fun saveAll(diaryId: Int) {
        viewModelScope.launch {

            try {
                _state.value.photos.forEach {
                    addPhotoUseCase(it)
                }

                _event.value = PhotoFlowEvent.Exit

            } catch (e: Exception) {
                Log.e("PHOTO_FLOW", "SAVE ERROR", e)
            }
        }
    }

    // RESET
    fun reset() {
        _state.value = PhotoFlowState()
        UnsavedChangesController.clear()
    }

}




