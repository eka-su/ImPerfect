package com.example.imperfect.feature.analysis.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.kit.container.SheetContainer
import com.example.imperfect.core.ui.kit.controls.MakeupToggle

@Composable
fun MakeupSettingsSection(
    hasMakeup: Boolean,
    onMakeupChange: (Boolean) -> Unit
) {
    SheetContainer {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            //LEFT TEXT
            Text(
                text = "Есть макияж?",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.weight(1f)
            )

            //RIGHT TOGGLE
            MakeupToggle(
                hasMakeup = hasMakeup,
                onChange = onMakeupChange
            )
        }
    }
}


