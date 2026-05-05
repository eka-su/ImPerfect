package com.example.imperfect.core.ui.kit.bottombar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.imperfect.core.navigation.BottomNavConfig
import com.example.imperfect.core.navigation.Router
import com.example.imperfect.core.navigation.isSelected

@Composable
fun BottomBarItems(
    currentRoute: String?,
    router: Router
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        BottomNavConfig.items.forEach { item ->
            BottomBarItem(
                item = item,
                isSelected = item.screen.isSelected(currentRoute),
                onClick = {
                    router.navigateToBottomTab(item.screen)
                }
            )
        }
    }
}