package com.example.imperfect.core.ui.kit.button

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imperfect.R
import com.example.imperfect.core.ui.designsystem.theme.Background
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = BluePrimary,
    contentColor: Color = Background,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 2.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        content()
    }
}


@Preview(showBackground = true)
@Composable
fun PrimaryButtonSizePreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        PrimaryButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Обычная кнопка")
        }

        PrimaryButton(
            onClick = {},
            modifier = Modifier.width(160.dp)
        ) {
            Text("Узкая")
        }

        PrimaryButton(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Высокая кнопка")
        }

        PrimaryButton(
            onClick = {},
            containerColor = Color.Red,
            contentColor = Color.White,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text("Удалить")
        }

        PrimaryButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text("С иконкой")
        }
    }
}