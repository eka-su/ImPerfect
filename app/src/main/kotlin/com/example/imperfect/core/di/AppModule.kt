package com.example.imperfect.core.di

import org.koin.dsl.module

object AppModule {

    // DATABASE
    val databaseModule = module {
        // TODO: Room DB + DAO:
        // diary, photo, analysis, nutrition, products, triggers, user_state, reports
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
        // PhotoRepository
        // AnalysisRepository
        // NutritionRepository
        // ProductRepository
        // TriggerRepository
        // RecommendationRepository
        // UserStateRepository
        // ReportRepository
    }

    // USE CASES
    val useCaseModule = module {
        // TODO: добавить use case
        // Auth: login/register/reset
        // Diary: get/save
        // Photo: save/get
        // Analysis: analyze photo
        // Nutrition: add/edit food + water
        // Products: add/get/favorite
        // Triggers: get/save
        // Recommendations: get
        // UserState: save lifestyle/skin/health
        // Reports: generate/get
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
    }

    val modules = listOf(
        databaseModule,
        networkModule,
        repositoryModule,
        useCaseModule,
        viewModelModule
    )
}