package com.example.imperfect.core.ui.kit.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.ui.designsystem.theme.IconGray
import com.example.imperfect.core.ui.designsystem.theme.InputBackground
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary

@Composable
fun InputContainer(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = InputBackground,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 14.dp, vertical = 14.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.labelMedium.copy(
                color = TextSecondary
            ),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.labelMedium,
                        color = IconGray
                    )
                }
                innerTextField()
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun InputContainerPreview() {

    var text by remember { mutableStateOf("") }

    InputContainer(
        value = text,
        onValueChange = { text = it },
        placeholder = "Введите текст..."
    )
}