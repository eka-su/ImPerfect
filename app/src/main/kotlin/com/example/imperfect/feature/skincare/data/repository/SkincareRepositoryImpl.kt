package com.example.imperfect.feature.skincare.data.repository

import com.example.imperfect.core.database.entity.CareRoutineItemEntity
import com.example.imperfect.core.database.entity.CareRoutineTimeLinkEntity
import com.example.imperfect.core.database.entity.SkincareProductEntity
import com.example.imperfect.feature.skincare.data.local.source.SkincareLocalDataSource
import com.example.imperfect.feature.skincare.domain.model.CareProduct
import com.example.imperfect.feature.skincare.domain.repository.SkincareRepository
import kotlinx.coroutines.flow.map
import com.example.imperfect.feature.skincare.data.mapper.toDomain
import com.example.imperfect.feature.skincare.domain.model.CareRoutine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SkincareRepositoryImpl(
    private val local: SkincareLocalDataSource
) : SkincareRepository {

    override fun getProducts() =
        local.getProducts().map { list ->
            list.map { it.toDomain() }
        }

    override fun searchProducts(query: String) =
        local.searchProducts(query).map { list ->
            list.map { it.toDomain() }
        }

    override fun getFavorites() =
        local.getFavorites().map { list ->
            list.map { it.toDomain() }
        }

    override fun getRecentProducts() =
        local.getRecentProducts().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun getProductById(
        id: Int
    ): CareProduct? {

        val product =
            local.getProductById(id)
                ?: return null

        return CareProduct(
            id = product.id,
            name = product.name,
            brand = product.brand,
            category = null,
            description = product.description,
            imagePath = product.imagePath,
            isFavorite = product.isFavorite,
            createdAt = product.createdAt
        )
    }

    override suspend fun addProduct(
        product: CareProduct
    ): Int {

        return local.insertProduct(
            SkincareProductEntity(
                id = 0,
                name = product.name,
                brand = product.brand,
                categoryId = product.category?.id,
                description = product.description,
                imagePath = product.imagePath,
                createdAt = product.createdAt,
                isFavorite = product.isFavorite
            )
        ).toInt()
    }

    override suspend fun updateProduct(
        product: CareProduct
    ) {

        local.updateProduct(
            SkincareProductEntity(
                id = product.id,
                name = product.name,
                brand = product.brand,
                categoryId = product.category?.id,
                description = product.description,
                imagePath = product.imagePath,
                createdAt = product.createdAt,
                isFavorite = product.isFavorite
            )
        )
    }

    override fun getTodayRoutine(
        diaryId: Int
    ) =
        local.getRoutine(diaryId).map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun addProductToRoutine(
        diaryId: Int,
        productId: Int,
        timeIds: List<Int>
    ) {

        val routineId =
            local.insertRoutineItem(
                CareRoutineItemEntity(
                    diaryId = diaryId,
                    productId = productId,
                    isUsed = true
                )
            ).toInt()

        timeIds.forEach { timeId ->

            local.insertTimeLink(
                CareRoutineTimeLinkEntity(
                    careItemId = routineId,
                    timeId = timeId
                )
            )
        }
    }

    override suspend fun removeFromRoutine(
        routineId: Int
    ) {
        local.deleteRoutine(routineId)
    }

    override fun getCategories() =
        local.getCategories().map { list ->
            list.map { it.toDomain() }
        }

    override fun getTimeSlots() =
        local.getTimeSlots().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun deleteProduct(
        productId: Int
    ) {
        local.deleteProduct(productId)
    }

    override suspend fun markRoutineUsed(
        routineId: Int,
        used: Boolean
    ) {
        local.markRoutineUsed(
            routineId,
            used
        )
    }

    override fun getRoutineHistory( //пока нет аналитики
        fromDate: String,
        toDate: String
    ): Flow<List<CareRoutine>> {
        return flowOf(emptyList())
    }

    override suspend fun addTimeToRoutine(
        routineId: Int,
        timeId: Int
    ) {
        local.insertTimeLink(
            CareRoutineTimeLinkEntity(
                careItemId = routineId,
                timeId = timeId
            )
        )
    }

    override suspend fun removeTimeFromRoutine(
        routineId: Int,
        timeId: Int
    ) {

        local.deleteTimeLink(
            routineId,
            timeId
        )

        val count =
            local.getTimeLinkCount(
                routineId
            )

        if (count == 0) {
            local.deleteRoutine(
                routineId
            )
        }
    }
}