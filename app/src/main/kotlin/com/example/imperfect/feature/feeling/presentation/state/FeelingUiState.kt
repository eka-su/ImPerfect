package com.example.imperfect.feature.feeling.presentation.state

import com.example.imperfect.feature.feeling.domain.model.FeelingOption

data class FeelingUiState(

    val screenId: Int = 0,
    val title: String = "",
    val options: List<FeelingOption> = emptyList(),
    val note: String = "",
    val initialSelectedIds: Set<Int> = emptySet(),
    val initialNote: String = ""
) {
    val selectedIds: Set<Int>
        get() =
            options
                .filter { it.selected }
                .map { it.id }
                .toSet()

    val hasChanges: Boolean
        get() =
            selectedIds != initialSelectedIds ||
                    note != initialNote
}
