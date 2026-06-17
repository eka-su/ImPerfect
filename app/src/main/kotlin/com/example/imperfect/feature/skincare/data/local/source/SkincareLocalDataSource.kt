package com.example.imperfect.feature.skincare.data.local.source

import com.example.imperfect.core.database.dao.SkincareDao
import com.example.imperfect.core.database.entity.CareRoutineItemEntity
import com.example.imperfect.core.database.entity.CareRoutineTimeLinkEntity
import com.example.imperfect.core.database.entity.SkincareProductEntity

class SkincareLocalDataSource(
    private val dao: SkincareDao
) {

    fun getProducts() =
        dao.getProductsWithCategory()

    fun searchProducts(query: String) =
        dao.searchProductsWithCategory(query)

    fun getFavorites() =
        dao.getFavoriteProducts()

    fun getRecentProducts() =
        dao.getRecentProducts()

    suspend fun getProductById(id: Int) =
        dao.getProductById(id)

    suspend fun insertProduct(
        product: SkincareProductEntity
    ) =
        dao.insertProduct(product)

    suspend fun updateProduct(
        product: SkincareProductEntity
    ) =
        dao.updateProduct(product)

    fun getRoutine(
        diaryId: Int
    ) =
        dao.getRoutineWithDetails(diaryId)

    suspend fun insertRoutineItem(
        item: CareRoutineItemEntity
    ) =
        dao.insertRoutineItem(item)

    suspend fun insertTimeLink(
        link: CareRoutineTimeLinkEntity
    ) =
        dao.insertTimeLink(link)

    suspend fun deleteRoutine(
        routineId: Int
    ) =
        dao.deleteRoutineById(routineId)

    fun getCategories() =
        dao.getCategories()

    fun getTimeSlots() =
        dao.getTimeSlots()

    suspend fun deleteProduct(
        productId: Int
    ) =
        dao.deleteProduct(productId)


    suspend fun markRoutineUsed(
        routineId: Int,
        used: Boolean
    ) =
        dao.markRoutineUsed(
            routineId,
            used
        )

    suspend fun deleteTimeLink(
        routineId: Int,
        timeId: Int
    ) =
        dao.deleteTimeLink(
            routineId,
            timeId
        )

    suspend fun getTimeLinkCount(
        routineId: Int
    ) =
        dao.getTimeLinkCount(
            routineId
        )

}