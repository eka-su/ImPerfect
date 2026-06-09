package com.example.imperfect.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.imperfect.core.session.UserSession
import com.example.imperfect.core.database.AppDatabase
import com.example.imperfect.core.database.seed.DatabaseSeeder
import com.example.imperfect.core.database.seed.LookupSeeder
import com.example.imperfect.core.network.RetrofitClient
import com.example.imperfect.feature.analysis.data.remote.AnalysisApi
import com.example.imperfect.feature.analysis.data.remote.AnalysisRemoteSource
import com.example.imperfect.feature.analysis.data.repository.AnalysisRepositoryImpl
import com.example.imperfect.feature.analysis.data.source.AnalysisContextLocalSource
import com.example.imperfect.feature.analysis.domain.repository.AnalysisRepository
import com.example.imperfect.feature.analysis.domain.usecase.AnalyzePhotosUseCase
import com.example.imperfect.feature.analysis.domain.usecase.DeleteFullAnalysisUseCase
import com.example.imperfect.feature.analysis.domain.usecase.GetAnalysisContextUseCase
import com.example.imperfect.feature.analysis.domain.usecase.GetSavedAnalysisUseCase
import com.example.imperfect.feature.analysis.domain.usecase.SaveAnalysisContextUseCase
import com.example.imperfect.feature.analysis.domain.usecase.SaveAnalysisUseCase
import com.example.imperfect.feature.analysis.presentation.viewmodel.AnalysisViewModel
import com.example.imperfect.feature.diary.data.repository.DiaryRepositoryImpl
import com.example.imperfect.feature.diary.domain.repository.DiaryRepository
import com.example.imperfect.feature.diary.domain.usecase.GetOrCreateDiaryDayUseCase
import com.example.imperfect.feature.diary.presentation.viewmodel.DiaryViewModel
import com.example.imperfect.feature.photo.data.repository.LookupRepositoryImpl
import com.example.imperfect.feature.photo.data.repository.PhotoRepositoryImpl
import com.example.imperfect.feature.photo.data.source.LookupLocalSource
import com.example.imperfect.feature.photo.data.source.PhotoLocalSource
import com.example.imperfect.feature.photo.domain.repository.LookupRepository
import com.example.imperfect.feature.photo.domain.repository.PhotoRepository
import com.example.imperfect.feature.photo.domain.usecase.AddPhotoUseCase
import com.example.imperfect.feature.photo.domain.usecase.DeletePhotoUseCase
import com.example.imperfect.feature.photo.domain.usecase.GetPhotoTypesUseCase
import com.example.imperfect.feature.photo.domain.usecase.GetPhotosUseCase
import com.example.imperfect.feature.photo.domain.usecase.ValidatePhotosUseCase
import com.example.imperfect.feature.photo.presentation.viewmodel.PhotoViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    //пока каждый раз пересоздаем базу
    val databaseModule = module {

        single {
            val context = get<Context>()

            context.deleteDatabase("imperfect.db")

            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "imperfect.db"
            )
                .addCallback(object : RoomDatabase.Callback() {

                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                    }

                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)

                    }
                })
                .build()
        }

        // DAO
        single { get<AppDatabase>().userDao() }
        single { get<AppDatabase>().diaryDao() }
        single { get<AppDatabase>().photoDao() }
        single { get<AppDatabase>().analysisDao() }
        single { get<AppDatabase>().lookupDao() }
        single { get<AppDatabase>().analysisContextDao() }

        // Seeders
        single {
            DatabaseSeeder(
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }

        single { LookupSeeder(get()) }
    }

    // NETWORK
    val networkModule = module {
        // TODO: Retrofit API:
        // auth, recommendations, products?

        single { RetrofitClient.api }

        single<AnalysisApi> {
            RetrofitClient.api
        }
    }



    // REPOSITORIES
    val repositoryModule = module {
        // TODO: реазлизоват репозитории
        // AuthRepository
        // NutritionRepository
        // ProductRepository
        // TriggerRepository
        // RecommendationRepository
        // UserStateRepository
        // ReportRepository

        //Photo
        single<PhotoLocalSource> {
            PhotoLocalSource(get())
        }

        single<PhotoRepository> {
            PhotoRepositoryImpl(get())
        }

        //Analysis
        single {
            AnalysisRemoteSource(get())
        }

        single<AnalysisRepository> {
            AnalysisRepositoryImpl(
                get(),
                get(),
                get(),
                get(),
                get()
            )
        }

        single {
            DeleteFullAnalysisUseCase(get())
        }

        single { AnalysisContextLocalSource(get()) }

        single { SaveAnalysisContextUseCase(get()) }

        //Diary
        single<DiaryRepository> {
            DiaryRepositoryImpl(get(),
                get(),
                get(),
                get(),
            )
        }

        //Lookup
        single { LookupLocalSource(get()) }

        single<LookupRepository> {
            LookupRepositoryImpl(get())
        }

        //Session
        single { UserSession() }

    }

    // USE CASES
    val useCaseModule = module {
        // TODO: добавить use case
        // Auth: login/register/reset
        // Nutrition: add/edit food + water
        // Products: add/get/favorite
        // Triggers: get/save
        // Recommendations: get
        // UserState: save lifestyle/skin/health
        // Reports: generate/get

        //Photo
        single {
            AddPhotoUseCase(get())
        }
        single {
            GetPhotosUseCase(get())
        }
        single {
            DeletePhotoUseCase(get())
        }
        single {
            ValidatePhotosUseCase()
        }

        //Analysis
        single {
            AnalyzePhotosUseCase(get())
        }
        single {
            SaveAnalysisUseCase(get())
        }

        single {
            AnalysisContextLocalSource(get())
        }

        factory {
            GetAnalysisContextUseCase(get())
        }

        factory {
            GetSavedAnalysisUseCase(get())
        }

        //Diary
        single {
            GetOrCreateDiaryDayUseCase(get())
        }

        //Lookup
        single {
            GetPhotoTypesUseCase(get())
        }

    }

    // VIEWMODELS
    val viewModelModule = module {
        // TODO: подключить viewModel
        // AuthViewModel
        // HomeViewModel
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
                get(),
                get(),
            )
        }

        viewModel {
            AnalysisViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
            )
        }

        viewModel {
            DiaryViewModel(get())
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

