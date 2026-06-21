package com.example.imperfect.feature.diary.data.repository

import android.util.Log
import com.example.imperfect.core.session.UserSession
import com.example.imperfect.core.database.dao.AnalysisDao
import com.example.imperfect.core.database.dao.DiaryDao
import com.example.imperfect.core.database.dao.FeelingDao
import com.example.imperfect.core.database.dao.PhotoDao
import com.example.imperfect.core.database.dao.SkincareDao
import com.example.imperfect.core.database.entity.SkinDiaryDayEntity
import com.example.imperfect.feature.analysis.domain.interpretation.calculateSkinHealthPercent
import com.example.imperfect.feature.diary.domain.model.DiaryFullUi
import com.example.imperfect.feature.diary.domain.model.DiaryPhotoPreview
import com.example.imperfect.feature.diary.domain.repository.DiaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class DiaryRepositoryImpl(
    private val dao: DiaryDao,
    private val photoDao: PhotoDao,
    private val analysisDao: AnalysisDao,
    private val userSession: UserSession,
    private val skincareDao: SkincareDao,
    private val feelingDao: FeelingDao,
) : DiaryRepository {

    override suspend fun getOrCreateDay(date: LocalDate): DiaryFullUi {
        val userId = userSession.userId

        Log.e("USER_CHECK", "session userId = $userId")

        val existing = dao.getByDate(userId, date.toString())
        Log.e("USER_CHECK", "existing day = $existing")


        val entity = if (existing != null) {
            existing
        } else {
            val id = dao.insertDay(
                SkinDiaryDayEntity(
                    userId = userId,
                    date = date.toString()
                )
            )

            dao.getById(id.toInt())
                ?: throw IllegalStateException("Diary not found")
        }

        val photos = photoDao.getPhotos(entity.id).map {
            DiaryPhotoPreview(
                id = it.id,
                filePath = it.filePath
            )
        }

        val analysis = analysisDao.getLatestByDiaryId(entity.id)

        val skincare = skincareDao.getRoutineWithDetails(entity.id).first()

        val skinScreen = feelingDao.getScreenByCode("skin_feeling")
        val healthScreen = feelingDao.getScreenByCode("health_feeling")

        val skinFeeling = skinScreen?.let {
            feelingDao.getSelectedOptionIds(entity.id, it.id)
        } ?: emptyList()

        val healthFeeling = healthScreen?.let {
            feelingDao.getSelectedOptionIds(entity.id, it.id)
        } ?: emptyList()

        return DiaryFullUi(
            id = entity.id,
            userId = userId,
            date = entity.date,
            photos = photos,
            analysisId = analysis?.id,
            analysisPercent = analysis?.let {
                calculateSkinHealthPercent(it.totalDetections)
            },
            skincare = skincare,
            skinFeeling = skinFeeling.map { it.toString() },
            healthFeeling = healthFeeling.map { it.toString() }
        )
    }

    override fun observeDay(date: LocalDate): Flow<DiaryFullUi> {
        val userId = userSession.userId

        return dao.observeByDate(userId, date.toString())
            .flatMapLatest { entity ->

                val skinScreen = feelingDao.getScreenByCode("SKIN")
                val healthScreen = feelingDao.getScreenByCode("HEALTH")

                val skinFeelingFlow =
                    skinScreen?.let {
                        feelingDao.observeSelectedOptionNames(entity.id, it.id)
                    } ?: flowOf(emptyList())

                val healthFeelingFlow =
                    healthScreen?.let {
                        feelingDao.observeSelectedOptionNames(entity.id, it.id)
                    } ?: flowOf(emptyList())

                combine(
                    skincareDao.getRoutineWithDetails(entity.id),
                    skinFeelingFlow,
                    healthFeelingFlow
                ) { skincare, skinFeeling, healthFeeling ->

                    val photos = photoDao.getPhotos(entity.id)

                    val analysis = analysisDao.getLatestByDiaryId(entity.id)

                    DiaryFullUi(
                        id = entity.id,
                        userId = userId,
                        date = entity.date,
                        photos = photos.map {
                            DiaryPhotoPreview(it.id, it.filePath)
                        },
                        analysisId = analysis?.id,
                        analysisPercent = analysis?.let {
                            calculateSkinHealthPercent(it.totalDetections)
                        },
                        skincare = skincare,
                        skinFeeling = skinFeeling,
                        healthFeeling = healthFeeling
                    )
                }
            }
    }

    override suspend fun getMarkedDates(): Set<LocalDate> {

        val skin = feelingDao.getScreenByCode("SKIN")?.id ?: return emptySet()
        val health = feelingDao.getScreenByCode("HEALTH")?.id ?: return emptySet()

        return dao.getFilledDays(skin, health)
            .map { LocalDate.parse(it.date) }
            .toSet()
    }

    override fun observeMarkedDates(): Flow<Set<LocalDate>> {
        return flow {
            val skin = feelingDao.getScreenByCode("SKIN")?.id
            val health = feelingDao.getScreenByCode("HEALTH")?.id

            if (skin == null || health == null) {
                emit(emptySet())
                return@flow
            }

            emitAll(
                dao.observeFilledDays(skin, health)
                    .map { list ->
                        list.map { LocalDate.parse(it.date) }.toSet()
                    }
            )
        }
    }

}