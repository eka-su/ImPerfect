package com.example.imperfect.feature.photo.presentation.event

sealed class PhotoFlowEvent {

    data class OpenAnalysis(
        val photoIds: List<Int>,
        val diaryId: Int
    ) : PhotoFlowEvent()

    object Exit : PhotoFlowEvent()
}