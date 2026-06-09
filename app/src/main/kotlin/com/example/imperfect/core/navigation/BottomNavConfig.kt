package com.example.imperfect.core.navigation

import com.example.imperfect.R

data class BottomNavItem(
    val screen: Screen,
    val iconRes: Int
)

object BottomNavConfig {
    val items = listOf(
        BottomNavItem(Screen.Recommendations, R.drawable.ic_recommendations),
        BottomNavItem(Screen.Triggers, R.drawable.ic_triggers),
        BottomNavItem(Screen.Home, R.drawable.ic_home),
        BottomNavItem(Screen.Diary, R.drawable.ic_diary),
        BottomNavItem(Screen.Analytics, R.drawable.ic_analytics),
        BottomNavItem(Screen.Settings, R.drawable.ic_settings)
    )
}