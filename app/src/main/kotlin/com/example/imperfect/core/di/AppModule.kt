package com.example.imperfect.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.imperfect.core.database.AppDatabase
import com.example.imperfect.feature.photo.data.repository.PhotoRepositoryImpl
import com.example.imperfect.feature.photo.data.source.PhotoLocalSource
import com.example.imperfect.feature.photo.domain.repository.PhotoRepository
import com.example.imperfect.feature.photo.domain.usecase.AddPhotoUseCase
import com.example.imperfect.feature.photo.domain.usecase.DeletePhotoUseCase
import com.example.imperfect.feature.photo.domain.usecase.GetPhotosUseCase
import com.example.imperfect.feature.photo.domain.usecase.ValidatePhotosUseCase
import com.example.imperfect.feature.photo.presentation.viewmodel.PhotoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val databaseModule = module {
        // TODO: Room DB + DAO:
        // diary, analysis, nutrition, products, triggers, user_state, reports

        single {
            val context = get<Context>()

            //удаляем базу каждый запуск, пока тестовый режим
            context.deleteDatabase("imperfect.db")
            Room.databaseBuilder(
                get(),
                AppDatabase::class.java,
                "imperfect.db"
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        db.execSQL("""
                            INSERT INTO skin_diary_day (id, user_id, date)
                            VALUES (1, 1, '2026-01-01')
                        """)

                        db.execSQL("INSERT INTO photo_view_type (id, code, name) VALUES (1, 'FRONT', 'Front')")
                        db.execSQL("INSERT INTO photo_view_type (id, code, name) VALUES (2, 'LEFT', 'Left')")
                        db.execSQL("INSERT INTO photo_view_type (id, code, name) VALUES (3, 'RIGHT', 'Right')")

                        db.execSQL("INSERT INTO photo_analysis_status (id, code, name) VALUES (1, 'PENDING', 'Pending')")
                        db.execSQL("INSERT INTO photo_analysis_status (id, code, name) VALUES (2, 'DONE', 'Done')")
                    }
                })
                .build()
        }

        single { get<AppDatabase>().diaryDao() }
        single { get<AppDatabase>().photoDao() }
        single { get<AppDatabase>().analysisDao() }
        single { get<AppDatabase>().lookupDao() }
        single { get<AppDatabase>().userDao() }
    }


    // NETWORK
    val networkModule = module {
        // TODO: Retrofit API:
        // auth, photo analysis, recommendations, products?
    }

    // REPOSITORIES
    val repositoryModule = module {
        // TODO: реазлизоват репозитории
        // AuthRepository
        // DiaryRepository
        // AnalysisRepository
        // NutritionRepository
        // ProductRepository
        // TriggerRepository
        // RecommendationRepository
        // UserStateRepository
        // ReportRepository
        single<PhotoLocalSource> {
            PhotoLocalSource(get())
        }
        single<PhotoRepository> {
            PhotoRepositoryImpl(get())
        }
    }

    // USE CASES
    val useCaseModule = module {
        // TODO: добавить use case
        // Auth: login/register/reset
        // Diary: get/save
        // Analysis: analyze photo
        // Nutrition: add/edit food + water
        // Products: add/get/favorite
        // Triggers: get/save
        // Recommendations: get
        // UserState: save lifestyle/skin/health
        // Reports: generate/get
        single { AddPhotoUseCase(get()) }
        single { GetPhotosUseCase(get()) }
        single { DeletePhotoUseCase(get()) }
        single { ValidatePhotosUseCase() }
    }

    // VIEWMODELS
    val viewModelModule = module {
        // TODO: подключить viewModel
        // AuthViewModel
        // HomeViewModel
        // DiaryViewModel
        // AnalysisViewModel
        // NutritionViewModel
        // ProductViewModel
        // TriggerViewModel
        // RecommendationViewModel
        // UserStateViewModel
        // ReportsViewModel
        viewModel {
            PhotoViewModel(
                get(),
                get(),
            )
        }
    }

    val modules = listOf(
        databaseModule,
        networkModule,
        repositoryModule,
        useCaseModule,
        viewModelModule
    )
}
