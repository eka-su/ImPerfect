package com.example.imperfect.core.navigation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.imperfect.core.ui.kit.bottombar.BottomBar
import com.example.imperfect.core.utils.UnsavedChangesController
import com.example.imperfect.feature.analysis.presentation.screen.AnalysisResultScreen
import com.example.imperfect.feature.analysis.presentation.screen.SkinTypesGuideScreen
import com.example.imperfect.feature.analysis.presentation.viewmodel.AnalysisViewModel
import com.example.imperfect.feature.analytics.presentation.AnalyticsScreen
import com.example.imperfect.feature.diary.presentation.screen.DiaryScreen
import com.example.imperfect.feature.diary.presentation.viewmodel.DiaryViewModel
import com.example.imperfect.feature.home.presentation.HomeScreen
import com.example.imperfect.feature.photo.presentation.flow.screen.PhotoFlowScreen
import com.example.imperfect.feature.photo.presentation.viewmodel.PhotoViewModel
import com.example.imperfect.feature.recommendations.presentation.RecommendationsScreen
import com.example.imperfect.feature.settings.presentation.SettingsScreen
import com.example.imperfect.feature.splash.presentation.SplashScreen
import com.example.imperfect.feature.triggers.presentation.TriggersScreen

@Composable
fun AppNavHost() {

    DisposableEffect(Unit) {
        Log.d("NAV", "AppNavHost MOUNTED")

        onDispose {
            Log.d("NAV", "AppNavHost DISPOSED")
        }
    }

    navArgument(Screen.AnalysisResult.ARG_DIARY_ID) {
        type = NavType.StringType
        defaultValue = ""
        nullable = true
    }


    val navTraceId = remember { System.currentTimeMillis() }
    Log.d("NAV_TRACE", "AppNavHost CREATED trace=$navTraceId")

    val navController = rememberNavController()
    val router = remember { Router(navController) }

    val currentRoute = navController
        .currentBackStackEntryAsState()
        .value
        ?.destination
        ?.route

    Log.d("NAV_TRACE", "currentRoute = $currentRoute")

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

    Log.d("NAV_TRACE", "showBottomBar=$showBottomBar isPhotoFlow=$isPhotoFlow")

    BackHandler {
        Log.d("NAV_TRACE", "BACK PRESSED route=$currentRoute")

        if (isPhotoFlow) {
            Log.d("NAV_TRACE", "Back blocked in PhotoFlow")
            return@BackHandler
        }

        if (UnsavedChangesController.hasChanges.value) {
            Log.d("NAV_TRACE", "Unsaved changes -> show dialog")
            showExitDialog = true
        } else {
            Log.d("NAV_TRACE", "router.back() called")
            router.back()
        }
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar && !isPhotoFlow) {
                Log.d("NAV_TRACE", "BottomBar visible")
                BottomBar(
                    currentRoute = currentRoute,
                    router = router
                )
            }
        }
    ) { padding ->

        Log.d("NAV_TRACE", "Scaffold content created")

        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(padding)
        ) {

            Log.d("NAV_TRACE", "NavHost BUILD START")

            // START FLOW
            composable(Screen.Splash.route) {
                Log.d("NAV_TRACE", "SplashScreen created")
                SplashScreen(router)
            }

            composable(Screen.Home.route) {
                Log.d("NAV_TRACE", "HomeScreen created")
                HomeScreen(router)
            }

            composable(Screen.Diary.route) {
                Log.d("NAV_TRACE", "DiaryScreen created")

                val viewModel = koinViewModel<DiaryViewModel>()

                DiaryScreen(
                    viewModel = viewModel,
                    router = router
                )
            }

            composable(Screen.Analytics.route) {
                Log.d("NAV_TRACE", "AnalyticsScreen created")
                AnalyticsScreen(router)
            }

            composable(Screen.Triggers.route) {
                Log.d("NAV_TRACE", "TriggersScreen created")
                TriggersScreen(router)
            }

            composable(Screen.Recommendations.route) {
                Log.d("NAV_TRACE", "RecommendationsScreen created")
                RecommendationsScreen(router)
            }

            composable(Screen.Settings.route) {
                Log.d("NAV_TRACE", "SettingsScreen created")
                SettingsScreen(router)
            }

            // PHOTO FLOW
            composable(Screen.PhotoFlow.route) { backStackEntry ->

                Log.d("PHOTO_FLOW", "PhotoFlow DESTINATION CREATED")
                Log.d("PHOTO_FLOW", "backStackEntry = $backStackEntry")

                val viewModel = koinViewModel<PhotoViewModel>(
                    viewModelStoreOwner = backStackEntry
                )

                Log.d("PHOTO_FLOW", "VM hash = ${viewModel.hashCode()}")

                PhotoFlowScreen(viewModel, router)

                Log.d("PHOTO_FLOW", "PhotoFlowScreen composed")
            }

            // ANALYSIS RESULT
            composable(
                route =
                    "${Screen.AnalysisResult.route}?" +
                            "${Screen.AnalysisResult.ARG_PHOTO_IDS}={${Screen.AnalysisResult.ARG_PHOTO_IDS}}" +
                            "&${Screen.AnalysisResult.ARG_ANALYSIS_ID}={${Screen.AnalysisResult.ARG_ANALYSIS_ID}}" +
                            "&${Screen.AnalysisResult.ARG_DIARY_ID}={${Screen.AnalysisResult.ARG_DIARY_ID}}",
                arguments = listOf(
                    navArgument(Screen.AnalysisResult.ARG_PHOTO_IDS) {
                        type = NavType.StringType
                        defaultValue = ""
                        nullable = true
                    },
                    navArgument(Screen.AnalysisResult.ARG_ANALYSIS_ID) {
                        type = NavType.StringType
                        defaultValue = ""
                        nullable = true
                    }
                )
            ) {
                Log.d("NAV_TRACE", "AnalysisResultScreen created")

                val viewModel = koinViewModel<AnalysisViewModel>()

                AnalysisResultScreen(
                    viewModel = viewModel,
                    router = router,
                    onBackClick = { router.openDiary() }
                )
            }

            // GUIDE SCREEN
            composable(Screen.SkinTypesGuide.route) {
                Log.d("NAV_TRACE", "SkinTypesGuideScreen created")

                SkinTypesGuideScreen(
                    onBack = { router.back() }
                )
            }
        }
    }
}