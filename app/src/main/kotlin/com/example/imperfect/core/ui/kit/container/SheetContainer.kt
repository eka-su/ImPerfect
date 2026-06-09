package com.example.imperfect.core.ui.kit.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.theme.Background
import com.example.imperfect.core.ui.designsystem.theme.GreyShadowLight

@Composable //белый контейнер с тенью, стандартный
fun SheetContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 19.dp,
                shape = RoundedCornerShape(22.dp),
                ambientColor = GreyShadowLight,
                spotColor = GreyShadowLight
            )
            .background(
                color = Background,
                shape = RoundedCornerShape(22.dp)
            )
    ) {
        content()
    }
}
