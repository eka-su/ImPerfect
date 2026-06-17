package com.example.imperfect.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.imperfect.core.database.entity.CareRoutineItemEntity
import com.example.imperfect.core.database.entity.CareRoutineTimeLinkEntity
import com.example.imperfect.core.database.entity.CareTimeSlotEntity
import com.example.imperfect.core.database.entity.SkincareCategoryEntity
import com.example.imperfect.core.database.entity.SkincareProductEntity
import com.example.imperfect.feature.skincare.data.local.relation.ProductWithCategory
import com.example.imperfect.feature.skincare.data.local.relation.RoutineWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface SkincareDao {

    // PRODUCTS
    @Query("SELECT * FROM skincare_products ORDER BY createdAt DESC")
    fun getProducts(): Flow<List<SkincareProductEntity>>

    @Query("""
        SELECT * FROM skincare_products
        WHERE name LIKE '%' || :q || '%' OR brand LIKE '%' || :q || '%'
    """)
    fun searchProducts(q: String): Flow<List<SkincareProductEntity>>

    @Insert
    suspend fun insertProduct(product: SkincareProductEntity): Long

    @Update
    suspend fun updateProduct(product: SkincareProductEntity)

    @Query("""
        SELECT *
        FROM skincare_products
        WHERE id = :id
    """)
    suspend fun getProductById(id: Int): SkincareProductEntity?

    @Query("""
        DELETE FROM skincare_products
        WHERE id = :productId
    """)
    suspend fun deleteProduct(productId: Int)

    // ROUTINE
    @Query("""
        SELECT * FROM care_routine_items
        WHERE diaryId = :diaryId
    """)
    fun getRoutineByDiary(diaryId: Int): Flow<List<CareRoutineItemEntity>>

    @Insert
    suspend fun insertRoutineItem(item: CareRoutineItemEntity): Long

    @Delete
    suspend fun deleteRoutineItem(item: CareRoutineItemEntity)

    @Query("""
        DELETE FROM care_routine_items
        WHERE id = :routineId
    """)
    suspend fun deleteRoutineById(routineId: Int)

    @Query("""
        UPDATE care_routine_items
        SET isUsed = :used
        WHERE id = :routineId
    """)
    suspend fun markRoutineUsed(routineId: Int, used: Boolean)

    // ROUTINE DETAILS
    @Transaction
    @Query("""
        SELECT *
        FROM care_routine_items
        WHERE diaryId = :diaryId
    """)
    fun getRoutineWithDetails(diaryId: Int): Flow<List<RoutineWithDetails>>

    // TIME LINKS
    @Query("""
        SELECT * FROM care_routine_time_links
        WHERE careItemId = :itemId
    """)
    fun getTimes(itemId: Int): Flow<List<CareRoutineTimeLinkEntity>>

    @Insert
    suspend fun insertTimeLink(link: CareRoutineTimeLinkEntity)

    @Query("""
        DELETE FROM care_routine_time_links
        WHERE careItemId = :itemId
    """)
    suspend fun deleteTimes(itemId: Int)

    @Query("""
        DELETE FROM care_routine_time_links
        WHERE careItemId = :routineId
        AND timeId = :timeId
    """)
    suspend fun deleteTimeLink(routineId: Int, timeId: Int)

    @Query("""
        SELECT COUNT(*) FROM care_routine_time_links
        WHERE careItemId = :itemId
    """)
    suspend fun getTimeCount(itemId: Int): Int

    @Query("""
        SELECT COUNT(*)
        FROM care_routine_time_links
        WHERE careItemId = :routineId
    """)
    suspend fun getTimeLinkCount(routineId: Int): Int

    // CATEGORY
    @Query("SELECT * FROM skincare_categories")
    fun getCategories(): Flow<List<SkincareCategoryEntity>>

    // TIME SLOTS
    @Query("SELECT * FROM care_time_slots")
    fun getTimeSlots(): Flow<List<CareTimeSlotEntity>>

    // PRODUCTS WITH CATEGORY
    @Transaction
    @Query("""
        SELECT *
        FROM skincare_products
        ORDER BY createdAt DESC
    """)
    fun getProductsWithCategory(): Flow<List<ProductWithCategory>>

    @Transaction
    @Query("""
        SELECT *
        FROM skincare_products
        WHERE name LIKE '%' || :query || '%'
           OR brand LIKE '%' || :query || '%'
    """)
    fun searchProductsWithCategory(query: String): Flow<List<ProductWithCategory>>

    @Transaction
    @Query("""
        SELECT *
        FROM skincare_products
        WHERE isFavorite = 1
        ORDER BY createdAt DESC
    """)
    fun getFavoriteProducts(): Flow<List<ProductWithCategory>>

    @Transaction
    @Query("""
        SELECT *
        FROM skincare_products
        ORDER BY createdAt DESC
        LIMIT 10
    """)
    fun getRecentProducts(): Flow<List<ProductWithCategory>>
}