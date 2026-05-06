package com.example.imperfect.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.imperfect.core.database.dao.AnalysisDao
import com.example.imperfect.core.database.dao.DiaryDao
import com.example.imperfect.core.database.dao.LookupDao
import com.example.imperfect.core.database.dao.PhotoDao
import com.example.imperfect.core.database.dao.UserDao
import com.example.imperfect.core.database.entity.AppUserEntity
import com.example.imperfect.core.database.entity.DiaryPhotoEntity
import com.example.imperfect.core.database.entity.MultiImageAnalysisEntity
import com.example.imperfect.core.database.entity.MultiImageAnalysisPhotoEntity
import com.example.imperfect.core.database.entity.PhotoAnalysisEntity
import com.example.imperfect.core.database.entity.PhotoAnalysisStatusEntity
import com.example.imperfect.core.database.entity.PhotoViewTypeEntity
import com.example.imperfect.core.database.entity.SkinAnalysisEntity
import com.example.imperfect.core.database.entity.SkinDiaryDayEntity
import com.example.imperfect.core.database.entity.SkinIssueEntity
import com.example.imperfect.core.database.entity.SkinIssueSummaryEntity

@Database(
    entities = [
        AppUserEntity::class,
        SkinDiaryDayEntity::class,
        DiaryPhotoEntity::class,
        PhotoViewTypeEntity::class,
        MultiImageAnalysisEntity::class,
        PhotoAnalysisEntity::class,
        SkinAnalysisEntity::class,
        SkinIssueEntity::class,
        PhotoAnalysisStatusEntity::class,
        SkinIssueSummaryEntity::class,
        MultiImageAnalysisPhotoEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun diaryDao(): DiaryDao
    abstract fun photoDao(): PhotoDao
    abstract fun analysisDao(): AnalysisDao
    abstract fun lookupDao(): LookupDao
    abstract fun userDao(): UserDao
}