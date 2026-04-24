package com.example.imperfect.feature.splash.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.imperfect.core.navigation.Router
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun SplashScreen(router: Router) {

    var navigated by remember { mutableStateOf(false) }

    fun goNext() {
        if (!navigated) {
            navigated = true
            router.openHome()
        }
    }

    // авто-переход через 2 секунды
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000)
        goNext()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { goNext() }, // тап по экрану
        contentAlignment = Alignment.Center
    ) {
        Text("Splash Screen")
    }
}