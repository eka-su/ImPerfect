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
import com.example.imperfect.feature.skincare.data.local.source.SkincareLocalDataSource
import com.example.imperfect.feature.skincare.data.repository.SkincareRepositoryImpl
import com.example.imperfect.feature.skincare.domain.repository.SkincareRepository
import com.example.imperfect.feature.skincare.domain.usecase.AddProductToRoutineUseCase
import com.example.imperfect.feature.skincare.domain.usecase.AddProductUseCase
import com.example.imperfect.feature.skincare.domain.usecase.DeleteProductUseCase
import com.example.imperfect.feature.skincare.domain.usecase.GetCategoriesUseCase
import com.example.imperfect.feature.skincare.domain.usecase.GetFavoritesUseCase
import com.example.imperfect.feature.skincare.domain.usecase.GetProductUseCase
import com.example.imperfect.feature.skincare.domain.usecase.GetProductsUseCase
import com.example.imperfect.feature.skincare.domain.usecase.GetRecentProductsUseCase
import com.example.imperfect.feature.skincare.domain.usecase.GetRoutineHistoryUseCase
import com.example.imperfect.feature.skincare.domain.usecase.GetTimeSlotsUseCase
import com.example.imperfect.feature.skincare.domain.usecase.GetTodayRoutineUseCase
import com.example.imperfect.feature.skincare.domain.usecase.MarkRoutineUsedUseCase
import com.example.imperfect.feature.skincare.domain.usecase.RemoveRoutineItemUseCase
import com.example.imperfect.feature.skincare.domain.usecase.RemoveTimeFromRoutineUseCase
import com.example.imperfect.feature.skincare.domain.usecase.SearchProductsUseCase
import com.example.imperfect.feature.skincare.domain.usecase.ToggleFavoriteUseCase
import com.example.imperfect.feature.skincare.domain.usecase.ToggleRoutineTimeUseCase
import com.example.imperfect.feature.skincare.domain.usecase.UpdateProductUseCase
import com.example.imperfect.feature.skincare.presentation.viewmodel.SkincareViewModel
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
        single { get<AppDatabase>().skincareDao() }

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

        // Skincare
        single {
            SkincareLocalDataSource(get())
        }

        single<SkincareRepository> {
            SkincareRepositoryImpl(get())
        }

    }

    // USE CASES
    val useCaseModule = module {
        // TODO: добавить use case
        // Auth: login/register/reset
        // Nutrition: add/edit food + water
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

        // Skincare

        single {
            AddProductUseCase(get())
        }

        single {
            UpdateProductUseCase(get())
        }

        single {
            GetProductsUseCase(get())
        }

        single {
            GetProductUseCase(get())
        }

        single {
            SearchProductsUseCase(get())
        }

        single {
            GetFavoritesUseCase(get())
        }

        single {
            GetRecentProductsUseCase(get())
        }

        single {
            GetTodayRoutineUseCase(get())
        }

        single {
            AddProductToRoutineUseCase(get())
        }

        single {
            RemoveRoutineItemUseCase(get())
        }

        single {
            GetCategoriesUseCase(get())
        }

        single {
            GetTimeSlotsUseCase(get())
        }

        single {
            DeleteProductUseCase(get())
        }

        single {
            ToggleFavoriteUseCase(get())
        }

        single {
            GetRoutineHistoryUseCase(get())
        }

        single {
            MarkRoutineUsedUseCase(get())
        }

        single {
            ToggleRoutineTimeUseCase(get())
        }

        single {
            RemoveTimeFromRoutineUseCase(get())
        }

    }

    // VIEWMODELS
    val viewModelModule = module {
        // TODO: подключить viewModel
        // AuthViewModel
        // HomeViewModel
        // NutritionViewModel
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

        viewModel {
            SkincareViewModel(
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
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



    }

    val modules = listOf(
        databaseModule,
        networkModule,
        repositoryModule,
        useCaseModule,
        viewModelModule
    )
}

