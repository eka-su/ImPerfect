package com.example.imperfect.feature.photo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imperfect.feature.diary.domain.usecase.GetOrCreateDiaryDayUseCase
import com.example.imperfect.feature.photo.domain.model.Photo
import com.example.imperfect.feature.photo.domain.model.PhotoViewType
import com.example.imperfect.feature.photo.domain.usecase.AddPhotoUseCase
import com.example.imperfect.feature.photo.domain.usecase.GetPhotoTypesUseCase
import com.example.imperfect.feature.photo.domain.usecase.ValidatePhotosUseCase
import com.example.imperfect.feature.photo.presentation.event.PhotoFlowEvent
import com.example.imperfect.feature.photo.presentation.state.FlowStage
import com.example.imperfect.feature.photo.presentation.state.PhotoFlowState
import com.example.imperfect.feature.photo.presentation.state.Source
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class PhotoViewModel(
    private val addPhotoUseCase: AddPhotoUseCase,
    private val validatePhotosUseCase: ValidatePhotosUseCase,
    private val getOrCreateDiaryDayUseCase: GetOrCreateDiaryDayUseCase,
    private val getPhotoTypesUseCase: GetPhotoTypesUseCase
) : ViewModel() {

    private val TAG = "PHOTO_DEBUG"

    // Internal State
    private var saving = false
    private var diaryId: Int? = null
    private var photoOrder: List<PhotoViewType> = emptyList()

    // UI State
    private val _state = MutableStateFlow(PhotoFlowState())
    val state: StateFlow<PhotoFlowState> = _state

    private val _event = MutableStateFlow<PhotoFlowEvent?>(null)
    val event: StateFlow<PhotoFlowEvent?> = _event

    // Initialization
    init {
        Log.d(TAG, "================ INIT =================")
        logFullState("INIT")
    }

    // Logging
    private fun logFullState(from: String) {
        val s = _state.value
        Log.d(TAG, "----------------------------------")
        Log.d(TAG, "FROM = $from")
        Log.d(TAG, "vm = ${this.hashCode()}")
        Log.d(TAG, "source = ${s.source}")
        Log.d(TAG, "stage = ${s.stage}")
        Log.d(TAG, "step = ${s.step}")
        Log.d(TAG, "photos = ${s.photos.size}")
        Log.d(TAG, "currentPhoto = ${s.currentPhoto?.filePath}")
        Log.d(TAG, "showExitDialog = ${s.showExitDialog}")
        Log.d(TAG, "diaryId = $diaryId")
        Log.d(TAG, "----------------------------------")
    }

    // Flow Start
    fun start(source: Source) {
        Log.d(TAG, "================ START =================")
        Log.d(TAG, "Starting PhotoFlow, source = $source")

        viewModelScope.launch {
            try {
                photoOrder = getPhotoTypesUseCase()
                Log.d(TAG, "Loading/creating diary for today...")
                val diary = getOrCreateDiaryDayUseCase(LocalDate.now())
                diaryId = diary.id
                Log.d(TAG, "Diary ready: $diaryId -> $diary")



                _state.value = PhotoFlowState(
                    source = source,
                    stage = FlowStage.GUIDE,
                    step = 0,
                    photos = emptyList(),
                    currentPhoto = null,
                    showExitDialog = false,
                    photoOrder = photoOrder
                )

                logFullState("AFTER START STATE")
            } catch (e: Exception) {
                Log.e(TAG, "Failed to start PhotoFlow", e)
            }
        }
    }

    // Photo Management
    fun addPhoto(path: String) {
        val diary = diaryId ?: run {
            Log.e(TAG, "Cannot add photo: diaryId is null")
            return
        }

        _state.update { state ->

            Log.d(TAG, "============== ADD PHOTO ==============")
            Log.d(TAG, "STATE STEP = ${state.step}")
            Log.d(TAG, "STATE STAGE = ${state.stage}")
            Log.d(TAG, "STATE PHOTOS COUNT = ${state.photos.size}")
            Log.d(TAG, "NEW PATH = $path")

            val currentType = photoOrder.getOrNull(state.step)

            Log.d(TAG, "CURRENT TYPE = $currentType")
            Log.d(TAG, "CURRENT TYPE ID = ${currentType?.id}")

            if (currentType == null) {
                Log.e(TAG, "No photo type for current step = ${state.step}")
                return@update state
            }

            state.photos.forEachIndexed { index, photo ->
                Log.d(
                    TAG,
                    "BEFORE[$index] type=${photo.viewTypeId} path=${photo.filePath}"
                )
            }

            val newPhoto = Photo(
                diaryId = diary,
                filePath = path,
                viewTypeId = currentType.id,
                createdAt = System.currentTimeMillis().toString()
            )

            Log.d(TAG, "NEW PHOTO = $newPhoto")

            Log.d(
                TAG,
                "Current photos before add: ${
                    state.photos.map {
                        it.viewTypeId to it.filePath
                    }
                }"
            )

            val updatedPhotos =
                state.photos.map {
                    if (it.viewTypeId == currentType.id) {
                        Log.d(
                            TAG,
                            "REPLACE PHOTO type=${it.viewTypeId}"
                        )
                        newPhoto
                    } else {
                        it
                    }
                }.let {
                    if (it.any { it.viewTypeId == currentType.id }) {
                        Log.d(TAG, "PHOTO TYPE ALREADY EXISTS -> REPLACED")
                        it
                    } else {
                        Log.d(TAG, "PHOTO TYPE NOT FOUND -> ADD NEW")
                        it + newPhoto
                    }
                }

            updatedPhotos.forEachIndexed { index, photo ->
                Log.d(
                    TAG,
                    "AFTER[$index] type=${photo.viewTypeId} path=${photo.filePath}"
                )
            }

            Log.d(
                TAG,
                "Updated photos after add: ${
                    updatedPhotos.map {
                        it.viewTypeId to it.filePath
                    }
                }"
            )

            Log.d(TAG, "TOTAL PHOTOS = ${updatedPhotos.size}")
            Log.d(TAG, "CURRENT PHOTO PATH = ${newPhoto.filePath}")
            Log.d(TAG, "============== END ADD PHOTO ==============")

            state.copy(
                photos = updatedPhotos,
                currentPhoto = newPhoto,
                stage = FlowStage.PREVIEW
            )
        }
    }

    fun canFinish(): Boolean {
        val photos = _state.value.photos
        val valid = validatePhotosUseCase(
            photos,
            photoOrder
        )
        Log.d(TAG, "Checking canFinish(): photos collected = ${photos.size}, valid = $valid")
        return valid
    }

    // Navigation / Stage Management
    fun setStage(stage: FlowStage) {
        Log.d(TAG, "============== SET STAGE ==============")
        Log.d(TAG, "OLD STAGE = ${_state.value.stage}")
        Log.d(TAG, "NEW STAGE = $stage")
        Log.d(TAG, "STEP = ${_state.value.step}")
        Log.d(TAG, "PHOTOS = ${_state.value.photos.size}")
        _state.update { it.copy(stage = stage) }
    }

    fun onContinue() {
        val isLast = _state.value.step == photoOrder.lastIndex

        val nextStep =
            if (isLast) _state.value.step
            else _state.value.step + 1

        _state.update {
            it.copy(
                step = nextStep,
                stage = FlowStage.CAPTURE,
                currentPhoto = null
            )
        }
    }

    // Preview Actions
    fun onPreviewBackClicked() {
        Log.d(TAG, "Preview back clicked")
        if (_state.value.photos.isNotEmpty()) {
            Log.d(TAG, "Showing exit dialog")
            _state.update { it.copy(showExitDialog = true) }
        } else {
            setStage(FlowStage.GUIDE)
        }
    }

    fun dismissExitDialog() {
        Log.d(TAG, "Dismiss exit dialog")
        _state.update { it.copy(showExitDialog = false) }
    }

    fun confirmExit() {
        Log.d(TAG, "Confirm exit clicked")
        reset()
        _state.update { it.copy(showExitDialog = false) }
    }

    // Save
    fun saveAll() {

        if (saving) {
            Log.w(TAG, "SAVE SKIPPED: already saving")
            return
        }

        saving = true

        Log.d(TAG, "================ SAVE ALL =================")

        val diary = diaryId ?: run {
            saving = false
            Log.e(TAG, "Cannot save: diaryId is null")
            return
        }

        val photos = _state.value.photos

        if (!validatePhotosUseCase(photos, photoOrder)) {
            saving = false
            Log.e(TAG, "Cannot save: invalid photos")
            return
        }

        viewModelScope.launch {

            try {

                val ids = photos.map { photo ->

                    val savedId =
                        addPhotoUseCase(
                            photo.copy(diaryId = diary)
                        ).toInt()

                    savedId
                }

                Log.d(TAG, "All photos saved successfully: $ids")

                _event.value =
                    PhotoFlowEvent.OpenAnalysis(
                        photoIds = ids,
                        diaryId = diary
                    )

            } catch (e: Exception) {

                Log.e(TAG, "SAVE ERROR", e)

            } finally {

                saving = false
            }
        }
    }

    // Reset
    fun reset() {
        Log.d(TAG, "Resetting PhotoFlowState")
        _state.update {
            PhotoFlowState(
                source = it.source,
                stage = FlowStage.SOURCE,
                step = 0,
                photos = emptyList(),
                currentPhoto = null,
                showExitDialog = false
            )
        }
        logFullState("AFTER RESET")
    }

}