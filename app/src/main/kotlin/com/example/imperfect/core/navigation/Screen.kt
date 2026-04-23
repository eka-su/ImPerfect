package com.example.imperfect.core.navigation

sealed class Screen(val route: String) {

    // START FLOW
    object Splash : Screen("splash") //заставка
    object Onboarding : Screen("onboarding")  //краткие инсрукции,первый запуск
    object Login : Screen("login")
    object Register : Screen("register")
    object ResetPassword : Screen("reset_password")

    // MAIN (BOTTOM NAV)
    object Home : Screen("home")
    object Diary : Screen("diary")
    object Analytics : Screen("analytics")
    object Triggers : Screen("triggers")
    object Recommendations : Screen("recommendations")
    object Settings : Screen("settings")

    // PHOTO & AI ANALYSIS
    object AnalysisResult : Screen("analysis_result/{photoId}") {
        fun createRoute(photoId: Long) = "analysis_result/$photoId"
    }
    object Compare : Screen("compare/{id1}/{id2}") {  //сравнение 2 фото
        fun createRoute(id1: String, id2: String) =
            "compare/$id1/$id2"
    }
    object SelectCompareDates : Screen("select_compare_dates") //?
    object PhotoViewer : Screen("photo_viewer/{photoId}") { //?
        fun createRoute(id: String) = "photo_viewer/$id"
    }
    object SkinTypesGuide : Screen("skin_types_guide")

    // GALLERY
    object Gallery : Screen("gallery")
    object GalleryFolder : Screen("gallery_folder/{folderId}") {
        fun createRoute(id: String) = "gallery_folder/$id"
    }
    object GalleryPhotoViewer : Screen("gallery_photo/{photoId}") {
        fun createRoute(id: String) = "gallery_photo/$id"
    }

    // SKINCARE / PRODUCTS
    object SkincareList : Screen("skincare_list")
    object AddProduct : Screen("add_product")
    object CreateProduct : Screen("create_product")
    object ProductDetail : Screen("product_detail/{productId}") {
        fun createRoute(id: String) = "product_detail/$id"
    }
    object ProductsBase : Screen("products_base")
    object Favorites : Screen("favorites")

    // NUTRITION & WATER
    object NutritionList : Screen("nutrition_list")
    object NutritionDetail : Screen("nutrition_detail/{dayId}") {
        fun createRoute(id: String) = "nutrition_detail/$id"
    }
    object EditNutrition : Screen("edit_nutrition")
    object CreateNutritionTag : Screen("create_nutrition_tag") //?
    object AddFoodProduct : Screen("add_food_product")
    object WaterTracker : Screen("water_tracker")//?
    object AddWater : Screen("add_water")//?

    // TRIGGERS & RECOMMENDATIONS
    object TriggerDetail : Screen("trigger_detail/{triggerId}") {
        fun createRoute(id: String) = "trigger_detail/$id"
    }
    object RecommendationDetail : Screen("recommendation_detail/{id}") {
        fun createRoute(id: String) = "recommendation_detail/$id"
    }

    // 🧍‍♀️ USER STATE
    object Lifestyle : Screen("lifestyle")
    object SkinCondition : Screen("skin_condition")
    object HealthFeeling : Screen("health_feeling")

    // REPORTS
    object Reports : Screen("reports")
}