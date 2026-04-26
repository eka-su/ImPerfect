package com.example.imperfect.core.ui.components.bottombar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.navigation.BottomNavItem
import com.example.imperfect.core.ui.theme.BluePrimary
import com.example.imperfect.core.ui.theme.IconGray

@Composable
fun BottomBarItem(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val tint = if (isSelected) BluePrimary else IconGray

    Box(
        modifier = Modifier
            .clickable { onClick() }
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(item.iconRes),
            contentDescription = item.screen.route,
            tint = tint,
            modifier = Modifier.size(20.dp)
        )
    }
}