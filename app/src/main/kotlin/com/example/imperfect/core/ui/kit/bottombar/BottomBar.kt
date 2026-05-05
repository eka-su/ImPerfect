package com.example.imperfect.core.ui.kit.bottombar

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.imperfect.core.navigation.Router
import com.example.imperfect.core.navigation.Screen
import com.example.imperfect.core.ui.designsystem.theme.ImPerfectTheme

@Composable
fun BottomBar(
    currentRoute: String?,
    router: Router
) {
    Box {
        BottomBarBackground()
        BottomBarContainer {
            BottomBarItems(
                currentRoute = currentRoute,
                router = router
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarPreview() {
    ImPerfectTheme {

        // фейк роутер для превью
        val fakeNavController = rememberNavController()
        val fakeRouter = remember { Router(fakeNavController) }

        BottomBar(
            currentRoute = Screen.Home.route,
            router = fakeRouter
        )
    }
}