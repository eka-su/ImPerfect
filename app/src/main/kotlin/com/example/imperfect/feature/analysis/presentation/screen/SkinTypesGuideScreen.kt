package com.example.imperfect.feature.analysis.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.icon.IconFilter
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary
import com.example.imperfect.core.ui.kit.container.SheetContainer
import com.example.imperfect.core.ui.kit.list.BackTextRow
import com.example.imperfect.feature.analysis.presentation.component.AcneTypeCard
import com.example.imperfect.feature.analysis.presentation.content.acneTypeDescription
import com.example.imperfect.feature.analysis.presentation.mapper.acneTypeUi

@Composable
fun SkinTypesGuideScreen(
    onBack: () -> Unit
) {
    val types = listOf(0, 1, 2, 3, 4, 5)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item { SkinTypesHeader(onBack) }

        items(types) { classId ->
            SkinTypeItem(classId)
        }

        item { SkinTypesInfoBlock() }

        item { Spacer(Modifier.height(20.dp)) }
    }
}

@Composable
private fun SkinTypesHeader(
    onBack: () -> Unit
) {
    Spacer(Modifier.height(20.dp))

    BackTextRow(
        text = "Типы акне",
        onClick = onBack
    )
}

@Composable
private fun SkinTypeItem(classId: Int) {
    val type = acneTypeUi(classId)

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        AcneTypeCard(ui = type)

        SheetContainer(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = acneTypeDescription(classId),
                    style = MaterialTheme.typography.labelMedium,
                    color = TextSecondary
                )
            }
        }
    }
}

@Composable
private fun SkinTypesInfoBlock() {
    Spacer(Modifier.height(10.dp))

    SheetContainer(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = IconFilter.QuestionMan.iconRes),
                    contentDescription = IconFilter.QuestionMan.contentDescription,
                    tint = BluePrimary,
                    modifier = Modifier.size(18.dp)
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = "Что это значит?",
                    style = MaterialTheme.typography.titleSmall,
                    color = BluePrimary
                )
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Это классификация типов высыпаний, которую использует система анализа. Она помогает определить преобладающие типы акне и отслеживать изменения кожи со временем.",
                style = MaterialTheme.typography.labelMedium,
                color = TextSecondary
            )
        }
    }
}
