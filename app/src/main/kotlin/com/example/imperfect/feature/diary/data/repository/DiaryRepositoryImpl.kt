package com.example.imperfect.feature.diary.data.repository

import android.util.Log
import com.example.imperfect.core.session.UserSession
import com.example.imperfect.core.database.dao.AnalysisDao
import com.example.imperfect.core.database.dao.DiaryDao
import com.example.imperfect.core.database.dao.PhotoDao
import com.example.imperfect.core.database.entity.SkinDiaryDayEntity
import com.example.imperfect.feature.analysis.domain.interpretation.calculateSkinHealthPercent
import com.example.imperfect.feature.diary.domain.model.DiaryFullUi
import com.example.imperfect.feature.diary.domain.model.DiaryPhotoPreview
import com.example.imperfect.feature.diary.domain.repository.DiaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class DiaryRepositoryImpl(
    private val dao: DiaryDao,
    private val photoDao: PhotoDao,
    private val analysisDao: AnalysisDao,
    private val userSession: UserSession
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

        return DiaryFullUi(
            id = entity.id,
            userId = userId,
            date = entity.date,
            photos = photos,
            analysisId = analysis?.id,
            analysisPercent = analysis?.let {
                calculateSkinHealthPercent(it.totalDetections)
            }
        )
    }

    override fun observeDay(date: LocalDate): Flow<DiaryFullUi> {
        val userId = userSession.userId

        return dao.observeByDate(userId, date.toString())
            .map { entity ->

                val photos = photoDao.getPhotos(entity.id).map {
                    DiaryPhotoPreview(
                        id = it.id,
                        filePath = it.filePath
                    )
                }

                val analysis = analysisDao.getLatestByDiaryId(entity.id)

                DiaryFullUi(
                    id = entity.id,
                    userId = userId,
                    date = entity.date,
                    photos = photos,
                    analysisId = analysis?.id,
                    analysisPercent = analysis?.let {
                        calculateSkinHealthPercent(it.totalDetections)
                    }
                )
            }
    }

    override suspend fun getMarkedDates(): Set<LocalDate> {

        return dao.getFilledDays()
            .map {
                LocalDate.parse(it.date)
            }
            .toSet()
    }
}