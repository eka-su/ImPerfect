package com.example.imperfect.core.navigation

import androidx.navigation.NavController
import java.net.URLEncoder

class Router(
    private val navController: NavController
) {

    // START FLOW
    fun openLogin() = navigateClear(Screen.Login.route)
    fun openHome() = navigateClear(Screen.Home.route)
    fun openRegister() = navController.navigate(Screen.Register.route)
    fun openResetPassword() = navController.navigate(Screen.ResetPassword.route)
    fun openOnboarding() = navController.navigate(Screen.Onboarding.route)

    // BOTTOM NAV
    fun navigateToBottomTab(screen: Screen) {
        navController.navigate(screen.route) {
            popUpTo(Screen.Home.route) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    // MAIN
    fun openDiary() = navController.navigate(Screen.Diary.route)
    fun openAnalytics() = navController.navigate(Screen.Analytics.route)
    fun openTriggers() = navController.navigate(Screen.Triggers.route)
    fun openRecommendations() = navController.navigate(Screen.Recommendations.route)
    fun openSettings() = navController.navigate(Screen.Settings.route)

    // PHOTO
    fun openAnalysis(photoId: Long) {
        navController.navigate(Screen.AnalysisResult.createRoute(photoId))
    }
    fun openCompare(id1: String, id2: String) {
        navController.navigate(Screen.Compare.createRoute(id1, id2))
    }
    fun openSelectCompareDates() {
        navController.navigate(Screen.SelectCompareDates.route)
    }
    fun openPhotoViewer(id: String) {
        navController.navigate(Screen.PhotoViewer.createRoute(id))
    }

    fun openPhotoFlow() {
        navController.navigate(Screen.PhotoFlow.route) {
            launchSingleTop = true
        }
    }
    fun openHomeAndClearPhotoFlow() {
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.PhotoFlow.route) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    // GALLERY
    fun openGallery() = navController.navigate(Screen.Gallery.route)
    fun openGalleryFolder(id: String) {
        navController.navigate(Screen.GalleryFolder.createRoute(id))
    }
    fun openGalleryPhoto(id: String) {
        navController.navigate(Screen.GalleryPhotoViewer.createRoute(id))
    }

    // SKINCARE
    fun openSkincare() = navController.navigate(Screen.SkincareList.route)
    fun openProduct(id: String) {
        navController.navigate(Screen.ProductDetail.createRoute(id))
    }

    // NUTRITION
    fun openNutrition() = navController.navigate(Screen.NutritionList.route)
    fun openNutritionDetail(id: String) {
        navController.navigate(Screen.NutritionDetail.createRoute(id))
    }
    fun openAddWater() {
        navController.navigate(Screen.AddWater.route)
    }

    // REPORTS
    fun openReports() = navController.navigate(Screen.Reports.route)

    // BACK
    fun back() {
        navController.popBackStack()
    }

    // PRIVATE HELPERS
    private fun navigateClear(route: String) {
        navController.navigate(route) {
            popUpTo(0)
            launchSingleTop = true
        }
    }
}