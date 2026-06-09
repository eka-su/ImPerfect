package com.example.imperfect.feature.diary.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.imperfect.R
import com.example.imperfect.core.ui.designsystem.icon.IconAction
import com.example.imperfect.core.ui.designsystem.icon.IconSize
import com.example.imperfect.core.ui.designsystem.icon.IconStyles
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.designsystem.theme.TextTertiary
import com.example.imperfect.core.ui.designsystem.theme.TextTitle
import com.example.imperfect.core.ui.kit.container.SheetContainer
import com.example.imperfect.core.ui.kit.icon.primitives.IconContainer

@Composable
fun DailyInputCard(
    icon: IconAction,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    SheetContainer(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Левая часть карточки /иконка
            DailyInputCardLeading(icon)

            Spacer(Modifier.width(12.dp))

            // Центральаня часть карточки /текст
            DailyInputCardContent(
                title = title,
                subtitle = subtitle,
                modifier = Modifier.weight(1f)
            )

            // Правая часть /стрелочка
            DailyInputCardTrailing()
        }
    }
}

@Composable
private fun DailyInputCardLeading(
    icon: IconAction
) {
    IconContainer(
        action = icon,
        size = IconSize.LARGE,
        style = IconStyles.Primary
    )
}

@Composable
private fun DailyInputCardContent(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = TextTitle
        )

        Spacer(Modifier.height(2.dp))

        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = TextTertiary
        )
    }
}

@Composable
private fun DailyInputCardTrailing() {
    Icon(
        painter = painterResource(R.drawable.ic_next),
        contentDescription = null,
        tint = BluePrimary,
        modifier = Modifier.size(18.dp)
    )
}