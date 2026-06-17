package com.example.imperfect.feature.diary.presentation.model

import com.example.imperfect.core.ui.designsystem.icon.IconAction

data class DiaryInputItem(
    val icon: IconAction,
    val title: String,
    val subtitle: String,
    val onClick: () -> Unit
)