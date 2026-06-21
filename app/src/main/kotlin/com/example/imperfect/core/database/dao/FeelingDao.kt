package com.example.imperfect.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.imperfect.core.database.entity.DiaryFeelingEntity
import com.example.imperfect.core.database.entity.DiaryFeelingNoteEntity
import com.example.imperfect.core.database.entity.FeelingOptionEntity
import com.example.imperfect.core.database.entity.FeelingScreenEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FeelingDao {

    // SCREENS
    @Query(
        """
        SELECT *
        FROM feeling_screens
        WHERE code = :code
        LIMIT 1
        """
    )
    suspend fun getScreenByCode(
        code: String
    ): FeelingScreenEntity?

    // OPTIONS
    @Query(
        """
        SELECT *
        FROM feeling_options
        WHERE screen_id = :screenId
        ORDER BY id
        """
    )
    suspend fun getOptions(
        screenId: Int
    ): List<FeelingOptionEntity>

    // NOTES
    @Query(
        """
        SELECT *
        FROM diary_feeling_notes
        WHERE diary_id = :diaryId
        AND screen_id = :screenId
        LIMIT 1
        """
    )
    suspend fun getNote(
        diaryId: Int,
        screenId: Int
    ): DiaryFeelingNoteEntity?

    @Upsert
    suspend fun upsertNote(
        note: DiaryFeelingNoteEntity
    )

    // SELECTIONS
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSelections(
        items: List<DiaryFeelingEntity>
    )

    @Query(
        """
        DELETE FROM diary_feelings
        WHERE diary_id = :diaryId
        AND feeling_option_id IN (
            SELECT id
            FROM feeling_options
            WHERE screen_id = :screenId
        )
        """
    )
    suspend fun clearSelectionsForScreen(
        diaryId: Int,
        screenId: Int
    )

    // SAVE
    @Transaction
    suspend fun saveScreenState(
        diaryId: Int,
        screenId: Int,
        selectedIds: Set<Int>,
        note: String
    ) {

        clearSelectionsForScreen(
            diaryId = diaryId,
            screenId = screenId
        )

        insertSelections(
            selectedIds.map {
                DiaryFeelingEntity(
                    diaryId = diaryId,
                    feelingOptionId = it
                )
            }
        )

        upsertNote(
            DiaryFeelingNoteEntity(
                diaryId = diaryId,
                screenId = screenId,
                notes = note
            )
        )
    }

    // READ SELECTED IDS
    @Query(
        """
        SELECT df.feeling_option_id
        FROM diary_feelings df
        INNER JOIN feeling_options fo
            ON fo.id = df.feeling_option_id
        WHERE df.diary_id = :diaryId
        AND fo.screen_id = :screenId
        """
    )
    suspend fun getSelectedOptionIds(
        diaryId: Int,
        screenId: Int
    ): List<Int>

    @Query(
        """
        SELECT df.feeling_option_id
        FROM diary_feelings df
        INNER JOIN feeling_options fo
            ON fo.id = df.feeling_option_id
        WHERE df.diary_id = :diaryId
        AND fo.screen_id = :screenId
        """
    )
    fun observeSelectedOptionIds(
        diaryId: Int,
        screenId: Int
    ): Flow<List<Int>>

    // READ SELECTED NAMES
    @Query(
        """
        SELECT fo.name
        FROM diary_feelings df
        INNER JOIN feeling_options fo
            ON fo.id = df.feeling_option_id
        WHERE df.diary_id = :diaryId
        AND fo.screen_id = :screenId
        """
    )
    fun observeSelectedOptionNames(
        diaryId: Int,
        screenId: Int
    ): Flow<List<String>>
}