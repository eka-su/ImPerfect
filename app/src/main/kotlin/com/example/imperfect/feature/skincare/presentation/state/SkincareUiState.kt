package com.example.imperfect.feature.skincare.presentation.state

import com.example.imperfect.feature.skincare.domain.model.CareProduct
import com.example.imperfect.feature.skincare.domain.model.CareRoutine
import com.example.imperfect.feature.skincare.domain.model.CareTime
import com.example.imperfect.feature.skincare.domain.model.ProductCategory

data class SkincareUiState(
    val products: List<CareProduct> = emptyList(),
    val favorites: List<CareProduct> = emptyList(),
    val recent: List<CareProduct> = emptyList(),

    val routine: List<CareRoutine> = emptyList(),
    val routineHistory: List<CareRoutine> = emptyList(),

    val categories: List<ProductCategory> = emptyList(),
    val timeSlots: List<CareTime> = emptyList(),

    val selectedProduct: CareProduct? = null,

    val isLoading: Boolean = false,
    val error: String? = null,

    val selectedCategoryId: Int? = null,
    val selectedTimeId: Int? = null,

    val routineMap: Map<Int, List<Int>> = emptyMap(),
    val currentDiaryId: Int = 0,


    val searchResults: List<CareProduct> = emptyList()

)

