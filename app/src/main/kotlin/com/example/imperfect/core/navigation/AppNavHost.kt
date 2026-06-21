package com.example.imperfect.core.navigation

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import com.example.imperfect.feature.feeling.presentation.screen.FeelingScreen
import com.example.imperfect.feature.feeling.presentation.viewmodel.FeelingViewModel
import com.example.imperfect.feature.home.presentation.HomeScreen
import com.example.imperfect.feature.photo.presentation.flow.screen.PhotoFlowScreen
import com.example.imperfect.feature.photo.presentation.viewmodel.PhotoViewModel
import com.example.imperfect.feature.recommendations.presentation.RecommendationsScreen
import com.example.imperfect.feature.settings.presentation.SettingsScreen
import com.example.imperfect.feature.skincare.domain.model.CareProduct
import com.example.imperfect.feature.skincare.presentation.screen.AddProductScreen
import com.example.imperfect.feature.skincare.presentation.screen.CreateProductScreen
import com.example.imperfect.feature.skincare.presentation.screen.FavoritesScreen
import com.example.imperfect.feature.skincare.presentation.screen.ProductDetailScreen
import com.example.imperfect.feature.skincare.presentation.screen.ProductsDatabaseScreen
import com.example.imperfect.feature.skincare.presentation.screen.SkincareScreen
import com.example.imperfect.feature.skincare.presentation.viewmodel.SkincareViewModel
import com.example.imperfect.feature.splash.presentation.SplashScreen
import com.example.imperfect.feature.triggers.presentation.TriggersScreen

@Composable
fun AppNavHost() {

    val feelingViewModel = koinViewModel<FeelingViewModel>()

    val skincareViewModel = koinViewModel<SkincareViewModel>()
    Log.d(
        "VM_CHECK",
        "AppNavHost VM hash=${skincareViewModel.hashCode()}"
    )

    val state by skincareViewModel.uiState.collectAsState()

    LaunchedEffect(state.currentDiaryId) {
        if (state.currentDiaryId != 0) {
            skincareViewModel.observeRoutine(state.currentDiaryId)
        }
    }

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

            // SKINCARE FLOW
            composable(
                route = Screen.SkincareList.route,
                arguments = listOf(
                    navArgument(Screen.SkincareList.ARG_DIARY_ID) { type = NavType.IntType }
                )
            ) { backStackEntry ->

                Log.d("SKINCARE_FLOW", "ENTER SkincareList route")
                Log.d("SKINCARE_FLOW", "backStackEntry.arguments = ${backStackEntry.arguments}")

                val diaryId = backStackEntry.arguments?.getInt(Screen.SkincareList.ARG_DIARY_ID)
                    ?: error("diaryId is missing")
                Log.d("SKINCARE_FLOW", "parsed diaryId = $diaryId")
                Log.d(
                    "VM_CHECK",
                    "SkincareList VM hash=${skincareViewModel.hashCode()}"
                )

                LaunchedEffect(diaryId) {
                    Log.d("SKINCARE_FLOW", "LaunchedEffect diaryId = $diaryId")
                    skincareViewModel.setDiaryId(diaryId)
                    Log.d("SKINCARE_FLOW", "setDiaryId called")
                    skincareViewModel.observeRoutine(diaryId)
                    Log.d("SKINCARE_FLOW", "observeRoutine called")
                }

                val state by skincareViewModel.uiState.collectAsState()
                Log.d("SKINCARE_FLOW", "UI state collected, currentDiaryId=${state.currentDiaryId}")

                SkincareScreen(
                    state = state,
                    onBack = {
                        Log.d("SKINCARE_FLOW", "BACK pressed")
                        router.back()
                    },
                    onAddClick = {
                        Log.d("SKINCARE_FLOW", "onAddClick")
                        router.openAddProduct()
                    },
                    onSelectTime = {
                        Log.d("SKINCARE_FLOW", "onSelectTime=$it")
                        skincareViewModel.onSelectTime(it)
                    },
                    onDelete = { productId, timeId ->
                        val diaryId = state.currentDiaryId ?: return@SkincareScreen

                        skincareViewModel.removeFromRoutine(
                            productId = productId,
                            timeId = timeId,
                            diaryId = diaryId
                        )
                    },
                    onEdit = { product ->
                        Log.d("SKINCARE_FLOW", "onEdit productId=${product.id} diaryId=$diaryId")
                        router.openProduct(product, diaryId)
                    }
                )
            }

            composable(Screen.AddProduct.route) {
                Log.d("SKINCARE_FLOW", "ENTER AddProduct")
                Log.d(
                    "VM_CHECK",
                    "AddProduct currentDiaryId=${state.currentDiaryId}"
                )
                Log.d(
                    "DIARY_DEBUG",
                    "AddProduct VM hash=${skincareViewModel.hashCode()}"
                )

                val state by skincareViewModel.uiState.collectAsState()
                Log.d(
                    "VM_CHECK",
                    "AddProduct currentDiaryId=${state.currentDiaryId}"
                )
                Log.d(
                    "DIARY_DEBUG",
                    "AddProduct currentDiaryId=${state.currentDiaryId}"
                )
                AddProductScreen(
                    state = state,
                    onBack = {
                        Log.d("AddProduct", "BACK pressed")
                        router.back()
                    },
                    onSearch = {
                        Log.d("AddProduct", "search=$it")
                        skincareViewModel.search(it)
                    },
                    onDelete = { id ->
                        Log.d("AddProduct", "delete productId=$id")
                        skincareViewModel.deleteProduct(id)
                    },
                    onEdit = { product ->
                        Log.d("AddProduct", "edit productId=${product.id}")
                        router.openProduct(product, state.currentDiaryId)
                    },
                    onCreateProduct = {
                        Log.d("AddProduct", "createProduct clicked")
                        router.openCreateProduct()
                    },
                    openProductsBase = {
                        Log.d("AddProduct", "openProductsBase clicked")
                        router.openProductsBase()
                    },
                    onOpenFavorites = {
                        Log.d("AddProduct", "openFavorites clicked")
                        router.openFavorites()
                    }
                )
            }

            composable(Screen.ProductsBase.route) {
                Log.d("SKINCARE_FLOW", "ENTER ProductsBase")
                val state by skincareViewModel.uiState.collectAsState()
                ProductsDatabaseScreen(
                    products = state.products,
                    onBack = {
                        Log.d("ProductsBase", "BACK pressed")
                        router.back()
                    },
                    onDelete = { id ->
                        Log.d("ProductsBase", "delete productId=$id")
                        skincareViewModel.deleteProduct(id)
                    },
                    onEdit = { product ->
                        Log.d("ProductsBase", "edit productId=${product.id}")
                        router.openProduct(product, state.currentDiaryId)
                    }
                )
            }

            composable(Screen.Favorites.route) {
                Log.d("SKINCARE_FLOW", "ENTER Favorites")
                val state by skincareViewModel.uiState.collectAsState()
                FavoritesScreen(
                    favorites = state.favorites,
                    onBack = {
                        Log.d("Favorites", "BACK pressed")
                        router.back()
                    },
                    onDelete = { id ->
                        Log.d("Favorites", "delete productId=$id")
                        skincareViewModel.deleteProduct(id)
                    },
                    onEdit = { product ->
                        Log.d("Favorites", "edit productId=${product.id}")
                        router.openProduct(product, state.currentDiaryId)
                    }
                )
            }

            composable(
                route = Screen.CreateProduct.route,
                arguments = listOf(
                    navArgument("productId") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    }
                )
            ) { backStackEntry ->
                Log.d("SKINCARE_FLOW", "ENTER CreateProduct")
                val productId = backStackEntry.arguments?.getString("productId")

                val state by skincareViewModel.uiState.collectAsState()

                val editingProduct = productId?.let { id ->
                    state.products.firstOrNull { it.id.toString() == id }
                }
                CreateProductScreen(
                    state = state,
                    editingProduct = editingProduct,
                    onBack = { router.back() },
                    onSelectCategory = { skincareViewModel.selectCategory(it) },
                    onSelectImage = { },
                    onDelete = {
                        editingProduct?.let {
                            skincareViewModel.deleteProduct(it.id)
                        }
                        router.openAddProduct()
                    },
                    onSave = { brand, name, description, categoryId, imagePath ->

                        if (editingProduct != null) {
                            skincareViewModel.updateProduct(
                                editingProduct.copy(
                                    brand = brand,
                                    name = name,
                                    description = description,
                                    category = state.categories.firstOrNull { it.id == categoryId },
                                    imagePath = imagePath
                                )
                            )
                        } else {
                            skincareViewModel.addProduct(
                                CareProduct(
                                    id = 0,
                                    name = name,
                                    brand = brand,
                                    description = description,
                                    category = state.categories.firstOrNull { it.id == categoryId },
                                    imagePath = imagePath,
                                    isFavorite = false,
                                    createdAt = System.currentTimeMillis().toString()
                                )
                            )
                        }

                        router.back()
                    }
                )
            }

            composable(
                route = Screen.ProductDetail.route,
                arguments = listOf(
                    navArgument("productId") { type = NavType.StringType },
                    navArgument("diaryId") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                Log.d("SKINCARE_FLOW", "ENTER ProductDetail")
                val productId = backStackEntry.arguments?.getString("productId")
                val diaryId = backStackEntry.arguments?.getString("diaryId")?.toIntOrNull() ?: -1
                Log.d(
                    "DIARY_DEBUG",
                    "ProductDetail route diaryId=$diaryId"
                )
                Log.d("ProductDetail", "productId=$productId diaryId=$diaryId")
                val state by skincareViewModel.uiState.collectAsState()
                val product = state.products.firstOrNull { it.id.toString() == productId }

                if (product == null) {
                    Log.d("ProductDetail", "product == null → loading")
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Загрузка...")
                    }
                } else {
                    Log.d("ProductDetail", "product loaded id=${product.id}")
                    ProductDetailScreen(
                        state = state,
                        product = product,
                        onBack = {
                            Log.d("ProductDetail", "BACK pressed")
                            router.back()
                        },
                        onToggleFavorite = {
                            Log.d("ProductDetail", "toggleFavorite product=${product.id}")
                            skincareViewModel.toggleFavorite(product)
                        },
                        onSelectTime = {
                            Log.d("ProductDetail", "selectTime=$it")
                            skincareViewModel.onSelectTime(it)
                        },
                        diaryId = diaryId,
                        viewModel = skincareViewModel,
                        onEdit = {
                            router.openCreateProduct(product.id)
                        },
                    )
                }
            }

            // Feelings
            composable(
                route = Screen.SkinFeeling.route
            ) { backStackEntry ->

                val diaryId =
                    backStackEntry.arguments
                        ?.getString("diaryId")
                        ?.toInt()
                        ?: return@composable

                FeelingScreen(
                    diaryId = diaryId,
                    screenCode = "SKIN",
                    viewModel = feelingViewModel,
                    router = router,
                )
            }

            composable(
                route = Screen.HealthFeeling.route
            ) { backStackEntry ->

                val diaryId =
                    backStackEntry.arguments
                        ?.getString("diaryId")
                        ?.toInt()
                        ?: return@composable

                FeelingScreen(
                    diaryId = diaryId,
                    screenCode = "HEALTH",
                    viewModel = feelingViewModel,
                    router = router,
                )
            }


        }
    }
}