package com.example.imperfect.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imperfect.core.database.entity.CareTimeSlotEntity
import com.example.imperfect.core.database.entity.FeelingOptionEntity
import com.example.imperfect.core.database.entity.FeelingScreenEntity
import com.example.imperfect.core.database.entity.PhotoAnalysisStatusEntity
import com.example.imperfect.core.database.entity.PhotoViewTypeEntity
import com.example.imperfect.core.database.entity.SkincareCategoryEntity
import com.example.imperfect.core.database.entity.SkincareProductEntity

@Dao
interface LookupDao {

    //Эти таблицы используются как справочники
    //типы отображения фото и статусы анализа (пока не используются)

    @Query("SELECT * FROM photo_view_type")
    suspend fun getViewTypes(): List<PhotoViewTypeEntity>

    @Query("SELECT * FROM photo_analysis_status")
    suspend fun getStatuses(): List<PhotoAnalysisStatusEntity>

    @Query("SELECT * FROM photo_view_type WHERE code = :code")
    suspend fun getByCode(code: String): PhotoViewTypeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertViewTypes(items: List<PhotoViewTypeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotoAnalysisStatuses(
        list: List<PhotoAnalysisStatusEntity>
    )

    // Для ухода
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSkincareCategories(
        items: List<SkincareCategoryEntity>
    )

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCareTimeSlots(
        items: List<CareTimeSlotEntity>
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSkincareProducts(
        items: List<SkincareProductEntity>
    )

    // Feeling
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeelingScreens(list: List<FeelingScreenEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeelingOptions(list: List<FeelingOptionEntity>)

}