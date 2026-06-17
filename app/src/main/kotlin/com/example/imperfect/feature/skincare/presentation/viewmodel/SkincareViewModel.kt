package com.example.imperfect.feature.skincare.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imperfect.feature.skincare.domain.usecase.AddProductToRoutineUseCase
import com.example.imperfect.feature.skincare.domain.usecase.GetTodayRoutineUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.imperfect.feature.skincare.domain.model.CareProduct
import com.example.imperfect.feature.skincare.domain.usecase.AddProductUseCase
import com.example.imperfect.feature.skincare.domain.usecase.GetCategoriesUseCase
import com.example.imperfect.feature.skincare.domain.usecase.GetFavoritesUseCase
import com.example.imperfect.feature.skincare.domain.usecase.GetProductsUseCase
import com.example.imperfect.feature.skincare.domain.usecase.GetRecentProductsUseCase
import com.example.imperfect.feature.skincare.domain.usecase.GetTimeSlotsUseCase
import com.example.imperfect.feature.skincare.domain.usecase.RemoveRoutineItemUseCase
import com.example.imperfect.feature.skincare.domain.usecase.SearchProductsUseCase
import com.example.imperfect.feature.skincare.domain.usecase.UpdateProductUseCase
import kotlinx.coroutines.launch
import com.example.imperfect.feature.skincare.domain.usecase.DeleteProductUseCase
import com.example.imperfect.feature.skincare.domain.usecase.GetProductUseCase
import com.example.imperfect.feature.skincare.domain.usecase.GetRoutineHistoryUseCase
import com.example.imperfect.feature.skincare.domain.usecase.MarkRoutineUsedUseCase
import com.example.imperfect.feature.skincare.domain.usecase.RemoveTimeFromRoutineUseCase
import com.example.imperfect.feature.skincare.domain.usecase.ToggleFavoriteUseCase
import com.example.imperfect.feature.skincare.domain.usecase.ToggleRoutineTimeUseCase
import com.example.imperfect.feature.skincare.presentation.state.SkincareUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.update

class SkincareViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductUseCase: GetProductUseCase,
    private val addProductUseCase: AddProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val searchProductsUseCase: SearchProductsUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val getRecentProductsUseCase: GetRecentProductsUseCase,
    private val getTodayRoutineUseCase: GetTodayRoutineUseCase,
    private val getRoutineHistoryUseCase: GetRoutineHistoryUseCase,
    private val addProductToRoutineUseCase: AddProductToRoutineUseCase,
    private val removeRoutineItemUseCase: RemoveRoutineItemUseCase,
    private val markRoutineUsedUseCase: MarkRoutineUsedUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getTimeSlotsUseCase: GetTimeSlotsUseCase,
    private val toggleRoutineTimeUseCase: ToggleRoutineTimeUseCase,
    private val removeTimeFromRoutineUseCase: RemoveTimeFromRoutineUseCase,
) : ViewModel() {

    companion object {
        private const val TAG = "care"
    }

    private val _uiState = MutableStateFlow(SkincareUiState())
    val uiState = _uiState.asStateFlow()

    init {
        Log.d(TAG, "init: loading initial data")
        loadInitial()
    }

    private fun loadInitial() {
        Log.d(TAG, "loadInitial: start")
        loadProducts()
        loadFavorites()
        loadRecent()
        loadCategories()
        loadTimeSlots()
    }

    // PRODUCTS
    fun loadProducts() {
        Log.d(TAG, "loadProducts: start")
        viewModelScope.launch {
            getProductsUseCase().collect {
                Log.d(TAG, "loadProducts: loaded ${it.size} products")
                _uiState.value = _uiState.value.copy(products = it)
            }
        }
    }

    private var searchJob: Job? = null

    fun search(query: String) {
        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            searchProductsUseCase(query).collect {
                _uiState.value = _uiState.value.copy(
                    searchResults = it
                )
            }
        }
    }

    fun loadProduct(productId: Int) { //не исопльзую, но пока оставлю
        Log.d(TAG, "loadProduct: productId=$productId")
        viewModelScope.launch {
            val product = getProductUseCase(productId)
            Log.d(TAG, "loadProduct: loaded product=$product")
            _uiState.value = _uiState.value.copy(selectedProduct = product)
        }
    }

    fun addProduct(product: CareProduct) {
        Log.d(TAG, "addProduct: product=$product")
        viewModelScope.launch {
            val now = java.time.LocalDate.now().toString()
            val newProduct = product.copy(createdAt = now)
            Log.d(TAG, "addProduct: newProduct=$newProduct")

            addProductUseCase(newProduct)
            Log.d(TAG, "addProduct: added to DB")

            _uiState.value = _uiState.value.copy(
                products = _uiState.value.products + newProduct,
                recent = listOf(newProduct) + _uiState.value.recent
            )
            Log.d(TAG, "addProduct: UI state updated")

            loadFavorites()
        }
    }

    fun updateProduct(product: CareProduct) {
        Log.d(TAG, "updateProduct: product=$product")
        viewModelScope.launch {
            updateProductUseCase(product)
            Log.d(TAG, "updateProduct: completed")
        }
    }

    fun deleteProduct(productId: Int) {
        Log.d(TAG, "deleteProduct: productId=$productId")
        viewModelScope.launch {
            deleteProductUseCase(productId)
            Log.d(TAG, "deleteProduct: completed")
        }
    }

    // FAVORITES
    fun toggleFavorite(product: CareProduct) {
        Log.d(TAG, "toggleFavorite: product=$product")
        viewModelScope.launch {
            toggleFavoriteUseCase(product)
            Log.d(TAG, "toggleFavorite: completed")
        }
    }

    fun loadFavorites() {
        Log.d(TAG, "loadFavorites: start")
        viewModelScope.launch {
            getFavoritesUseCase().collect {
                Log.d(TAG, "loadFavorites: loaded ${it.size} favorites")
                _uiState.value = _uiState.value.copy(favorites = it)
            }
        }
    }

    // RECENT
    fun loadRecent() {
        Log.d(TAG, "loadRecent: start")
        viewModelScope.launch {
            getRecentProductsUseCase().collect {
                Log.d(TAG, "loadRecent: loaded ${it.size} recent products")
                _uiState.value = _uiState.value.copy(recent = it)
            }
        }
    }

    //для будущей аналитики
    fun loadRoutineHistory(fromDate: String, toDate: String) {
        Log.d(TAG, "loadRoutineHistory: from=$fromDate to=$toDate")
        viewModelScope.launch {
            getRoutineHistoryUseCase(fromDate, toDate).collect {
                Log.d(TAG, "loadRoutineHistory: loaded ${it.size} history items")
                _uiState.value = _uiState.value.copy(routineHistory = it)
            }
        }
    }

    fun removeFromRoutine(productId: Int, timeId: Int, diaryId: Int) {
        viewModelScope.launch {

            val routineItem = _uiState.value.routine.firstOrNull { routine ->
                routine.product.id == productId &&
                        routine.times.any { it.id == timeId }
            } ?: return@launch

            removeTimeFromRoutineUseCase(
                routineId = routineItem.id,
                timeId = timeId
            )
        }
    }

    fun markRoutineUsed(routineId: Int, used: Boolean) { //может потом захочу отмечать вручную
        Log.d(TAG, "markRoutineUsed: routineId=$routineId used=$used")
        viewModelScope.launch {
            markRoutineUsedUseCase(routineId, used)
            Log.d(TAG, "markRoutineUsed: completed")
        }
    }

    fun toggleRoutineForProduct(
        product: CareProduct,
        diaryId: Int,
        timeId: Int
    ) {

        viewModelScope.launch {

            val routine = _uiState.value.routine
                .find {
                    it.product.id == product.id
                }

            toggleRoutineTimeUseCase(
                routine = routine,
                productId = product.id,
                diaryId = diaryId,
                timeId = timeId
            )
        }
    }

    fun observeRoutine(diaryId: Int) {
        viewModelScope.launch {
            getTodayRoutineUseCase(diaryId)
                .collect { routine ->
                    _uiState.update { it.copy(routine = routine) }
                }
        }
    }

    // LOOKUPS (CATEGORIES / TIME SLOTS)
    private fun loadCategories() {
        Log.d(TAG, "loadCategories: start")
        viewModelScope.launch {
            getCategoriesUseCase().collect {
                Log.d(TAG, "loadCategories: loaded ${it.size} categories")
                _uiState.value = _uiState.value.copy(categories = it)
            }
        }
    }

    private fun loadTimeSlots() {
        Log.d(TAG, "loadTimeSlots: start")
        viewModelScope.launch {
            getTimeSlotsUseCase().collect {
                Log.d(TAG, "loadTimeSlots: loaded ${it.size} time slots")
                Log.d(TAG, "TIME SLOTS = $it")
                _uiState.value = _uiState.value.copy(timeSlots = it)
            }
        }
    }

    fun selectCategory(id: Int) {
        Log.d(TAG, "selectCategory: id=$id")
        _uiState.value = _uiState.value.copy(selectedCategoryId = id)
    }

    fun onSelectTime(timeId: Int) {
        Log.d(TAG, "onSelectTime: timeId=$timeId")
        _uiState.value = _uiState.value.copy(selectedTimeId = timeId)
    }

    // DIARY CONTEXT
    fun setDiaryId(id: Int) {
        Log.d(TAG, "setDiaryId: id=$id")
        _uiState.value = _uiState.value.copy(currentDiaryId = id)
    }
}

