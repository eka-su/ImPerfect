package com.example.imperfect.core.utils

import androidx.compose.runtime.mutableStateOf

object UnsavedChangesController {
    var hasChanges = mutableStateOf(false)

    fun markChanged() {
        hasChanges.value = true
    }

    fun clear() {
        hasChanges.value = false
    }
}