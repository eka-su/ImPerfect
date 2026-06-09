package com.example.imperfect.core.ui.kit.controls

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.icon.IconAction
import com.example.imperfect.core.ui.designsystem.icon.IconStyles
import com.example.imperfect.core.ui.kit.icon.primitives.IconActionButton

@Composable
fun MakeupToggle(
    hasMakeup: Boolean,
    onChange: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // YES
        IconActionButton(
            action = IconAction.Select,
            onClick = { onChange(true) },
            style = if (hasMakeup)
                IconStyles.Filled
            else
                IconStyles.Default
        )

        // NO
        IconActionButton(
            action = IconAction.Close,
            onClick = { onChange(false) },
            style = if (!hasMakeup)
                IconStyles.Filled
            else
                IconStyles.Default
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MakeupTogglePreviewBothStates() {
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {

        MakeupToggle(
            hasMakeup = true,
            onChange = {}
        )

        MakeupToggle(
            hasMakeup = false,
            onChange = {}
        )
    }
}