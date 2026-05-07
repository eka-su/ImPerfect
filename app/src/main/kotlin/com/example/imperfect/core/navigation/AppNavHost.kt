package com.example.imperfect.core.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.imperfect.core.ui.kit.bottombar.BottomBar
import com.example.imperfect.core.utils.UnsavedChangesController
import com.example.imperfect.feature.analytics.presentation.AnalyticsScreen
import com.example.imperfect.feature.diary.presentation.DiaryScreen
import com.example.imperfect.feature.home.presentation.HomeScreen
import com.example.imperfect.feature.photo.presentation.flow.screen.PhotoFlowScreen
import com.example.imperfect.feature.photo.presentation.viewmodel.PhotoViewModel
import com.example.imperfect.feature.recommendations.presentation.RecommendationsScreen
import com.example.imperfect.feature.settings.presentation.SettingsScreen
import com.example.imperfect.feature.splash.presentation.SplashScreen
import com.example.imperfect.feature.triggers.presentation.TriggersScreen

@Composable
fun AppNavHost() {

    val navController = rememberNavController()
    val router = remember { Router(navController) }

    val currentRoute = navController
        .currentBackStackEntryAsState()
        .value
        ?.destination
        ?.route

    var showExitDialog by remember { mutableStateOf(false) }

    val noBottomBarScreens = listOf(
        Screen.Splash.route,
        Screen.Onboarding.route,
        Screen.Login.route,
        Screen.Register.route,
        Screen.ResetPassword.route
    )

    val showBottomBar = currentRoute !in noBottomBarScreens

    val isPhotoFlow = currentRoute == Screen.PhotoFlow.route

    BackHandler {
        if (isPhotoFlow) return@BackHandler

        if (UnsavedChangesController.hasChanges.value) {
            showExitDialog = true
        } else {
            router.back()
        }
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar && !isPhotoFlow) {
                BottomBar(
                    currentRoute = currentRoute,
                    router = router
                )
            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(padding)
        ) {

            // START FLOW
            composable(Screen.Splash.route) { SplashScreen(router) }
            // MAIN
            composable(Screen.Home.route) { HomeScreen(router) }
            composable(Screen.Diary.route) { DiaryScreen(router) }
            composable(Screen.Analytics.route) { AnalyticsScreen(router) }
            composable(Screen.Triggers.route) { TriggersScreen(router) }
            composable(Screen.Recommendations.route) { RecommendationsScreen(router) }
            composable(Screen.Settings.route) { SettingsScreen(router) }

            // PHOTO FLOW
            composable(Screen.PhotoFlow.route) { backStackEntry ->

                val viewModel = koinViewModel<PhotoViewModel>(
                    viewModelStoreOwner = backStackEntry
                )
                PhotoFlowScreen(
                    viewModel = viewModel,
                    router = router
                )
            }
        }
    }
}

