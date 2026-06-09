package com.example.imperfect.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.imperfect.core.database.entity.MultiImageAnalysisEntity
import com.example.imperfect.core.database.entity.MultiImageAnalysisPhotoEntity
import com.example.imperfect.core.database.entity.PhotoAnalysisEntity
import com.example.imperfect.core.database.entity.SkinAnalysisEntity
import com.example.imperfect.core.database.entity.SkinIssueEntity
import com.example.imperfect.core.database.entity.SkinIssueSummaryEntity

@Dao
interface AnalysisDao {

    // INSERT
    @Insert
    suspend fun insertMultiAnalysis(
        entity: MultiImageAnalysisEntity
    ): Long

    @Insert
    suspend fun insertPhotoAnalysis(
        entity: PhotoAnalysisEntity
    ): Long

    @Insert
    suspend fun insertSkinAnalysis(
        entity: SkinAnalysisEntity
    ): Long

    @Insert
    suspend fun insertMultiImageAnalysisPhoto(
        entity: MultiImageAnalysisPhotoEntity
    ): Long

    @Insert
    suspend fun insertIssues(
        list: List<SkinIssueEntity>
    )

    @Insert
    suspend fun insertSummary(
        list: List<SkinIssueSummaryEntity>
    )

    // GET
    @Query("""
        SELECT *
        FROM multi_image_analysis
        WHERE id = :multiAnalysisId
    """)
    suspend fun getMultiAnalysisById(
        multiAnalysisId: Int
    ): MultiImageAnalysisEntity?

    @Query("""
        SELECT pa.*
        FROM photo_analysis pa
        INNER JOIN multi_image_analysis_photo mip
            ON mip.photo_analysis_id = pa.id
        WHERE mip.multi_analysis_id = :multiAnalysisId
    """)
    suspend fun getPhotoAnalyses(
        multiAnalysisId: Int
    ): List<PhotoAnalysisEntity>

    @Query("""
        SELECT *
        FROM skin_analysis
        WHERE photo_analysis_id IN (:photoAnalysisIds)
    """)
    suspend fun getSkinAnalyses(
        photoAnalysisIds: List<Int>
    ): List<SkinAnalysisEntity>

    @Query("""
        SELECT *
        FROM skin_issue
        WHERE analysis_id = :skinAnalysisId
    """)
    suspend fun getIssues(
        skinAnalysisId: Int
    ): List<SkinIssueEntity>

    @Query("""
        SELECT *
        FROM skin_issue_summary
        WHERE analysis_id = :skinAnalysisId
    """)
    suspend fun getSummary(
        skinAnalysisId: Int
    ): List<SkinIssueSummaryEntity>

    // DELETE: частичное удаление (по уровням)
    @Query("""
        DELETE FROM skin_issue
        WHERE analysis_id IN (:skinAnalysisIds)
    """)
    suspend fun deleteIssuesBySkinAnalysisIds(
        skinAnalysisIds: List<Int>
    )

    @Query("""
        DELETE FROM skin_issue_summary
        WHERE analysis_id IN (:skinAnalysisIds)
    """)
    suspend fun deleteSummaryBySkinAnalysisIds(
        skinAnalysisIds: List<Int>
    )

    @Query("""
        DELETE FROM skin_analysis
        WHERE photo_analysis_id IN (:photoAnalysisIds)
    """)
    suspend fun deleteSkinAnalyses(
        photoAnalysisIds: List<Int>
    )

    @Query("""
        DELETE FROM multi_image_analysis_photo
        WHERE multi_analysis_id = :multiAnalysisId
    """)
    suspend fun deleteMultiImagePhotoLinks(
        multiAnalysisId: Int
    )

    @Query("""
        DELETE FROM photo_analysis
        WHERE id IN (:photoAnalysisIds)
    """)
    suspend fun deletePhotoAnalyses(
        photoAnalysisIds: List<Int>
    )

    @Query("""
        DELETE FROM multi_image_analysis
        WHERE id = :multiAnalysisId
    """)
    suspend fun deleteMultiAnalysis(
        multiAnalysisId: Int
    )

    // DELETE STRATEGY !!!!!!!!
    // Вся модель анализа кожи построена как связанный граф сущностей, где MultiImageAnalysis является корнем,
    // далее идут PhotoAnalysis, затем SkinAnalysis и самые глубокие сущности — SkinIssue и SkinIssueSummary.
    // Из-за такой вложенной структуры и наличия внешних ключей (ForeignKey) удаление данных нельзя выполнять
    // произвольно, иначе можно нарушить целостность базы или оставить "осиротевшие" записи. Поэтому удаление
    // всегда выполняется строго по направлению снизу вверх: сначала удаляются внутренние данные анализа
    // (issues, summary, skin_analysis), затем промежуточные сущности и связи (photo_analysis и связи между таблицами),
    // и только после этого удаляется корневая запись multi_image_analysis.
    // FULL DELETE реализует полный обход всей цепочки данных и гарантирует корректное и безопасное удаление
    // всей структуры анализа без нарушения ограничений базы данных и без потери согласованности данных.

    // FULL DELETE
    @Transaction
    suspend fun deleteFullAnalysis(
        multiAnalysisId: Int
    ) {

        val photoAnalyses = getPhotoAnalyses(
            multiAnalysisId
        )

        val photoAnalysisIds = photoAnalyses.map {
            it.id
        }

        if (photoAnalysisIds.isEmpty()) {
            deleteMultiAnalysis(multiAnalysisId)
            return
        }

        val skinAnalyses = getSkinAnalyses(
            photoAnalysisIds
        )

        val skinAnalysisIds = skinAnalyses.map {
            it.id
        }

        if (skinAnalysisIds.isNotEmpty()) {

            deleteIssuesBySkinAnalysisIds(
                skinAnalysisIds
            )

            deleteSummaryBySkinAnalysisIds(
                skinAnalysisIds
            )
        }

        deleteSkinAnalyses(
            photoAnalysisIds
        )

        deleteMultiImagePhotoLinks(
            multiAnalysisId
        )

        deletePhotoAnalyses(
            photoAnalysisIds
        )

        deleteMultiAnalysis(
            multiAnalysisId
        )
    }

    // QUERY: быстрый доступ к последнему анализу дня
    @Query("""
    SELECT *
    FROM multi_image_analysis
    WHERE diary_id = :diaryId
    ORDER BY created_at DESC
    LIMIT 1
""")
    suspend fun getLatestByDiaryId(
        diaryId: Int
    ): MultiImageAnalysisEntity?
}