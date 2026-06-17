package com.example.imperfect.feature.diary.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imperfect.feature.diary.domain.repository.DiaryRepository
import com.example.imperfect.feature.diary.presentation.state.DiaryState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class DiaryViewModel(
    private val repository: DiaryRepository
) : ViewModel() {

    private val TAG = "PHOTO_DEBUG"

    // UI State
    private val _state = MutableStateFlow(DiaryState())
    val state = _state.asStateFlow()

    // Initialization
    init {
        Log.d(TAG, "")
        Log.d(TAG, "================ DIARY VM INIT =================")
        Log.d(TAG, "VM HASH = ${this.hashCode()}")
        Log.d(TAG, "INITIAL STATE = ${_state.value}")
        Log.d(TAG, "INITIAL DATE = ${_state.value.selectedDate}")

        viewModelScope.launch {
            repository.observeMarkedDates()
                .collect { dates ->
                    _state.update {
                        it.copy(markedDates = dates)
                    }
                }
        }

        loadDay(_state.value.selectedDate)
    }

    // Day Loading
    private fun loadDay(date: LocalDate) {

        Log.d(TAG, "")
        Log.d(TAG, "================ LOAD DAY =================")
        Log.d(TAG, "REQUESTED DATE = $date")
        Log.d(TAG, "VM HASH = ${this.hashCode()}")

        viewModelScope.launch {

            Log.d(TAG, "Coroutine started for loadDay($date)")

            try {

                Log.d(TAG, "Calling repository.getOrCreateDay($date)")

                val day = repository.getOrCreateDay(date)

                Log.d(TAG, "")
                Log.d(TAG, "================ DAY RECEIVED =================")
                Log.d(TAG, "DAY = $day")
                Log.d(TAG, "DAY ID = ${day.id}")
                Log.d(TAG, "DAY USER ID = ${day.userId}")
                Log.d(TAG, "DAY DATE = ${day.date}")
                Log.d(TAG, "DAY ANALYSIS ID = ${day.analysisId}")
                Log.d(TAG, "DAY ANALYSIS PERCENT = ${day.analysisPercent}")
                Log.d(TAG, "DAY PHOTOS COUNT = ${day.photos.size}")

                if (day.photos.isEmpty()) {
                    Log.d(TAG, "NO PHOTOS IN DAY")
                } else {

                    day.photos.forEachIndexed { index, photo ->

                        Log.d(TAG, "----------- PHOTO [$index] -----------")
                        Log.d(TAG, "PHOTO OBJECT = $photo")
                    }
                }

                _state.update {

                    Log.d(TAG, "")
                    Log.d(TAG, "================ STATE UPDATE =================")
                    Log.d(TAG, "OLD STATE = $it")

                    val newState = it.copy(
                        day = day,
                        selectedDate = date
                    )

                    Log.d(TAG, "NEW STATE = $newState")
                    Log.d("MARK", "MARKED FROM DB = ${it.markedDates}") // старое
                    Log.d("MARK", "NEW DAY = ${day.date}") // текущий день

                    newState
                }

                Log.d(TAG, "")
                Log.d(TAG, "================ START FLOW =================")
                Log.d(TAG, "Subscribing to observeDay($date)")

                repository.observeDay(date).collect { updatedDay ->

                    Log.d(TAG, "")
                    Log.d(TAG, "================ FLOW EMIT =================")
                    Log.d(TAG, "UPDATED DAY = $updatedDay")
                    Log.d(TAG, "UPDATED DAY ID = ${updatedDay.id}")
                    Log.d(TAG, "UPDATED DAY DATE = ${updatedDay.date}")
                    Log.d(TAG, "UPDATED DAY PHOTOS COUNT = ${updatedDay.photos.size}")

                    if (updatedDay.photos.isEmpty()) {

                        Log.d(TAG, "FLOW: NO PHOTOS")

                    } else {

                        updatedDay.photos.forEachIndexed { index, photo ->

                            Log.d(TAG, "FLOW PHOTO [$index] = $photo")
                        }
                    }

                    _state.update {

                        Log.d(TAG, "")
                        Log.d(TAG, "================ FLOW STATE UPDATE =================")
                        Log.d(TAG, "OLD FLOW STATE = $it")

                        val newState = it.copy(day = updatedDay)

                        Log.d(TAG, "NEW FLOW STATE = $newState")

                        newState
                    }
                }

            } catch (e: Exception) {

                Log.e(TAG, "")
                Log.e(TAG, "================ LOAD ERROR =================")
                Log.e(TAG, "DATE = $date")
                Log.e(TAG, "ERROR MESSAGE = ${e.message}")
                Log.e(TAG, "ERROR CAUSE = ${e.cause}")

                e.printStackTrace()
            }
        }
    }

    // Day Navigation (предыдущий день и некст день убрала из исолпьзования, но пока тут оставлю)
    fun prevDay() {

        val currentDate = _state.value.selectedDate
        val newDate = currentDate.minusDays(1)

        Log.d(TAG, "")
        Log.d(TAG, "================ PREV DAY =================")
        Log.d(TAG, "CURRENT DATE = $currentDate")
        Log.d(TAG, "NEW DATE = $newDate")

        _state.update {

            val newState = it.copy(selectedDate = newDate)

            Log.d(TAG, "STATE AFTER PREV DAY = $newState")

            newState
        }

        loadDay(newDate)
    }

    fun nextDay() {

        val currentDate = _state.value.selectedDate
        val newDate = currentDate.plusDays(1)

        Log.d(TAG, "")
        Log.d(TAG, "================ NEXT DAY =================")
        Log.d(TAG, "CURRENT DATE = $currentDate")
        Log.d(TAG, "NEW DATE = $newDate")

        _state.update {

            val newState = it.copy(selectedDate = newDate)

            Log.d(TAG, "STATE AFTER NEXT DAY = $newState")

            newState
        }

        loadDay(newDate)
    }

    fun selectDate(date: LocalDate) {
        loadDay(date)
    }

    // Week Navigation
    fun prevWeek() {

        val newDate =
            _state.value.selectedDate.minusWeeks(1)

        loadDay(newDate)
    }

    fun nextWeek() {

        val newDate =
            _state.value.selectedDate.plusWeeks(1)

        loadDay(newDate)
    }

    // Calendar Actions
    fun toggleCalendar() {

        _state.update {
            it.copy(
                expandedCalendar = !it.expandedCalendar
            )
        }
    }

    fun goToToday() {

        val today = LocalDate.now()

        loadDay(today)
    }
}


