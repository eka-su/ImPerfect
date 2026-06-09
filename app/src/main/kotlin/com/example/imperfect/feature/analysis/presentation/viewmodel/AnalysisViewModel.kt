package com.example.imperfect.feature.analysis.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imperfect.core.navigation.Screen
import com.example.imperfect.feature.analysis.domain.usecase.AnalyzePhotosUseCase
import com.example.imperfect.feature.analysis.domain.usecase.DeleteFullAnalysisUseCase
import com.example.imperfect.feature.analysis.domain.usecase.GetAnalysisContextUseCase
import com.example.imperfect.feature.analysis.domain.usecase.GetSavedAnalysisUseCase
import com.example.imperfect.feature.analysis.domain.usecase.SaveAnalysisContextUseCase
import com.example.imperfect.feature.analysis.domain.usecase.SaveAnalysisUseCase
import com.example.imperfect.feature.analysis.presentation.event.AnalysisEvent
import com.example.imperfect.feature.analysis.presentation.state.AnalysisState
import com.example.imperfect.feature.photo.domain.usecase.DeletePhotoUseCase
import com.example.imperfect.feature.photo.domain.usecase.GetPhotosUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnalysisViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val analyzePhotosUseCase: AnalyzePhotosUseCase,
    private val saveAnalysisUseCase: SaveAnalysisUseCase,
    private val getPhotosUseCase: GetPhotosUseCase,
    private val deletePhotoUseCase: DeletePhotoUseCase,
    private val deleteAnalysisUseCase: DeleteFullAnalysisUseCase,
    private val saveAnalysisContextUseCase: SaveAnalysisContextUseCase,
    private val getAnalysisContextUseCase: GetAnalysisContextUseCase,
    private val getSavedAnalysisUseCase: GetSavedAnalysisUseCase,
) : ViewModel() {

    private val TAG = "PHOTO_DEBUG"

    // UI State
    private val _state = MutableStateFlow(AnalysisState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<AnalysisEvent>()
    val event = _event.asSharedFlow()

    private val _hasMakeup = MutableStateFlow(false)
    val hasMakeup = _hasMakeup.asStateFlow()

    private val _note = MutableStateFlow("")
    val note = _note.asStateFlow()

    // Internal State
    private var multiAnalysisId: Int? = null
    private var analysisJob: Job? = null

    // Initialization
    init {
        Log.d(TAG, "================ VIEWMODEL INIT ================")
        observeNoteChanges()
    }

    // Start / Entry Point
    fun start() {

        Log.d(TAG, "================ START() CALLED ================")

        val diaryIdRaw = savedStateHandle.get<String>(Screen.AnalysisResult.ARG_DIARY_ID)
        val photoIdsRaw = savedStateHandle.get<String>(Screen.AnalysisResult.ARG_PHOTO_IDS)
        val analysisIdRaw = savedStateHandle.get<String>(Screen.AnalysisResult.ARG_ANALYSIS_ID)

        Log.d(TAG, "RAW diaryId = $diaryIdRaw")
        Log.d(TAG, "RAW photoIds = $photoIdsRaw")
        Log.d(TAG, "RAW analysisId = $analysisIdRaw")

        val diaryId = diaryIdRaw?.toIntOrNull()
        val analysisId = analysisIdRaw?.toIntOrNull()

        Log.d(TAG, "PARSED diaryId = $diaryId")
        Log.d(TAG, "PARSED analysisId = $analysisId")

        when {

            analysisId != null -> {
                Log.d(TAG, "MODE = LOAD EXISTING ANALYSIS")
                Log.d(TAG, "analysisId = $analysisId")

                multiAnalysisId = analysisId

                loadSavedAnalysis(analysisId)
                loadContext(analysisId)
            }

            photoIdsRaw.isNullOrBlank().not() -> {

                Log.d(TAG, "MODE = NEW ANALYSIS")

                val photoIds = photoIdsRaw
                    .orEmpty()
                    .split(",")
                    .mapNotNull {
                        val parsed = it.toIntOrNull()
                        Log.d(TAG, "PHOTO_ID parse: $it -> $parsed")
                        parsed
                    }

                Log.d(TAG, "FINAL PHOTO IDS = $photoIds")

                Log.d(TAG, "diaryId BEFORE ANALYZE = $diaryId")

                if (diaryId != null) {
                    analyze(diaryId, photoIds)
                } else {
                    Log.e(TAG, "diaryId IS NULL -> cannot start analysis")
                }
            }

            else -> {
                Log.e(TAG, "NO INPUT DATA FOUND IN SAVED STATE HANDLE")
            }
        }
    }

    // Analysis Flow
    private fun analyze(diaryId: Int, photoIds: List<Int>) {

        Log.d(TAG, "================ ANALYZE() ================")
        Log.d(TAG, "diaryId = $diaryId")
        Log.d(TAG, "photoIds = $photoIds")

        if (analysisJob?.isActive == true) {
            Log.w(TAG, "analysisJob already active -> skipping")
            return
        }

        analysisJob = viewModelScope.launch {

            try {
                Log.d(TAG, "SETTING LOADING = TRUE")
                _state.update { it.copy(isLoading = true) }

                val allPhotos = getPhotosUseCase(diaryId)

                Log.d(TAG, "ALL PHOTOS FROM DB = ${allPhotos.size}")
                allPhotos.forEach {
                    Log.d(TAG, "PHOTO DB: id=${it.id} viewType=${it.viewTypeId}")
                }

                val photos = allPhotos.filter { it.id in photoIds }

                Log.d(TAG, "FILTERED PHOTOS = ${photos.size}")
                photos.forEach {
                    Log.d(TAG, "SELECTED PHOTO: id=${it.id}")
                }

                val result = analyzePhotosUseCase(photos)

                Log.d(TAG, "ANALYSIS RESULT RECEIVED")
                Log.d(TAG, "images size = ${result.images.size}")
                Log.d(TAG, "summary = ${result.summary}")

                val id = saveAnalysisUseCase(diaryId, photos, result)

                Log.d(TAG, "SAVED ANALYSIS ID = $id")

                multiAnalysisId = id.toInt()

                _state.update {
                    it.copy(
                        isLoading = false,
                        result = result,
                        photos = photos
                    )
                }

                Log.d(TAG, "STATE UPDATED AFTER ANALYSIS")

                multiAnalysisId?.let {
                    Log.d(TAG, "LOADING CONTEXT FOR ID = $it")
                    loadContext(it)
                }

            } catch (e: Exception) {

                Log.e(TAG, "ANALYSIS FAILED", e)

                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }

                Log.e(TAG, "STATE UPDATED WITH ERROR = ${e.message}")
            }
        }
    }

    // Saved Analysis Loading
    private fun loadSavedAnalysis(analysisId: Int) {

        viewModelScope.launch {

            Log.d(TAG, "LOAD SAVED ANALYSIS id=$analysisId")

            try {
                _state.update { it.copy(isLoading = true) }

                val (result, photos) = getSavedAnalysisUseCase(analysisId)

                Log.d(TAG, "LOADED SAVED RESULT images=${result.images.size}")
                Log.d(TAG, "LOADED SAVED PHOTOS=${photos.size}")

                _state.update {
                    it.copy(
                        isLoading = false,
                        result = result,
                        photos = photos
                    )
                }

                Log.d(TAG, "STATE UPDATED FROM SAVED ANALYSIS")

            } catch (e: Exception) {

                Log.e(TAG, "LOAD SAVED FAILED", e)

                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

    // Context (Makeup / Note)
    fun setMakeup(value: Boolean) {
        Log.d(TAG, "SET MAKEUP = $value")
        _hasMakeup.value = value
        saveContext()
    }

    fun setNote(value: String) {
        Log.d(TAG, "SET NOTE = $value")
        _note.value = value
    }

    private fun saveContext() {

        viewModelScope.launch {

            val id = multiAnalysisId

            Log.d(TAG, "SAVE CONTEXT called, multiAnalysisId=$id")

            if (id == null) {
                Log.w(TAG, "SAVE CONTEXT SKIPPED: id is null")
                return@launch
            }

            try {
                saveAnalysisContextUseCase(
                    multiAnalysisId = id,
                    hasMakeup = _hasMakeup.value,
                    note = _note.value.takeIf { it.isNotBlank() }
                )

                Log.d(TAG, "CONTEXT SAVED OK")

            } catch (e: Exception) {
                Log.e(TAG, "SAVE CONTEXT FAILED", e)
            }
        }
    }

    private fun loadContext(multiId: Int) {

        viewModelScope.launch {

            Log.d(TAG, "LOAD CONTEXT multiId=$multiId")

            try {
                val context = getAnalysisContextUseCase(multiId)

                Log.d(TAG, "CONTEXT FROM DB = $context")

                if (context == null) {
                    Log.w(TAG, "NO CONTEXT FOUND")
                    return@launch
                }

                _hasMakeup.value = context.has_makeup == 1
                _note.value = context.note.orEmpty()

                Log.d(TAG, "CONTEXT APPLIED: makeup=${_hasMakeup.value}, note=${_note.value}")

            } catch (e: Exception) {
                Log.e(TAG, "LOAD CONTEXT FAILED", e)
            }
        }
    }

    // Reactive Note Observation
    private fun observeNoteChanges() {
        viewModelScope.launch {
            Log.d(TAG, "OBSERVE NOTE STARTED")

            _note
                .drop(1)
                .debounce(800)
                .collect {
                    Log.d(TAG, "NOTE DEBOUNCED -> SAVE CONTEXT")
                    saveContext()
                }
        }
    }

    // Edit Flow
    fun onEditRequested() {

        Log.d(TAG, "================ EDIT REQUESTED ================")
        Log.d(TAG, "multiAnalysisId = $multiAnalysisId")

        viewModelScope.launch {

            val id = multiAnalysisId

            if (id == null) {
                Log.e(TAG, "EDIT FAILED: multiAnalysisId is NULL")
                return@launch
            }

            try {
                Log.d(TAG, "DELETING ANALYSIS id=$id")

                deleteAnalysisUseCase(id)

                state.value.photos.forEach {
                    Log.d(TAG, "DELETING PHOTO id=${it.id}")
                    deletePhotoUseCase(it.id)
                }

                _state.update {
                    it.copy(
                        photos = emptyList(),
                        result = null
                    )
                }

                Log.d(TAG, "STATE CLEARED AFTER DELETE")

                Log.d(TAG, "EMIT OPEN PHOTO FLOW EVENT")
                _event.emit(AnalysisEvent.OpenPhotoFlow)

            } catch (e: Exception) {
                Log.e(TAG, "EDIT FAILED", e)
            }
        }
    }

}