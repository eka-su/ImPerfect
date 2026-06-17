package com.example.imperfect.feature.skincare.domain.repository

import com.example.imperfect.feature.skincare.domain.model.CareProduct
import com.example.imperfect.feature.skincare.domain.model.CareRoutine
import com.example.imperfect.feature.skincare.domain.model.CareTime
import com.example.imperfect.feature.skincare.domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow

interface SkincareRepository {

    // PRODUCTS
    fun getProducts(): Flow<List<CareProduct>>

    fun searchProducts(query: String): Flow<List<CareProduct>>

    fun getFavorites(): Flow<List<CareProduct>>

    fun getRecentProducts(): Flow<List<CareProduct>>

    suspend fun getProductById(id: Int): CareProduct?

    suspend fun addProduct(product: CareProduct): Int

    suspend fun updateProduct(product: CareProduct)

    // ROUTINE
    fun getTodayRoutine(
        diaryId: Int
    ): Flow<List<CareRoutine>>

    suspend fun addProductToRoutine(
        diaryId: Int,
        productId: Int,
        timeIds: List<Int>
    )

    suspend fun removeFromRoutine(
        routineId: Int
    )

    // LOOKUPS
    fun getCategories():
            Flow<List<ProductCategory>>

    fun getTimeSlots():
            Flow<List<CareTime>>

    suspend fun deleteProduct(
        productId: Int
    )

    suspend fun markRoutineUsed(
        routineId: Int,
        used: Boolean
    )

    fun getRoutineHistory(
        fromDate: String,
        toDate: String
    ): Flow<List<CareRoutine>>

    suspend fun addTimeToRoutine(
        routineId: Int,
        timeId: Int
    )

    suspend fun removeTimeFromRoutine(
        routineId: Int,
        timeId: Int
    )

}