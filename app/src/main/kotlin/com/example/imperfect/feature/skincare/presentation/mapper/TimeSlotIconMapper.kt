package com.example.imperfect.feature.skincare.presentation.mapper

import com.example.imperfect.core.ui.designsystem.icon.IconFilter

object TimeSlotIconMapper {

    fun iconFor(code: String): IconFilter {
        return when (code) {
            "MORNING" -> IconFilter.Morning
            "EVENING" -> IconFilter.Evening
            else -> IconFilter.Alltime
        }
    }
}