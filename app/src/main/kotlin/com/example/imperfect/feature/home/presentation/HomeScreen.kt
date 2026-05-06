package com.example.imperfect.feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.imperfect.core.navigation.Router
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary
import com.example.imperfect.core.ui.designsystem.theme.TextTitle
import com.example.imperfect.core.ui.kit.icon.clickable.BackButton
import com.example.imperfect.core.ui.kit.icon.clickable.CloseButton
import com.example.imperfect.core.ui.kit.button.PrimaryButton
import com.example.imperfect.core.ui.kit.container.SheetContainer
import com.example.imperfect.core.ui.kit.icon.notclickable.CameraContainer
import com.example.imperfect.core.ui.kit.icon.notclickable.GalleryContainer

//@Composable
//fun HomeScreen(router: Router) {
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text("Home Screen")
//    }
//}
//

@Composable ///просто проверка элементов пока
fun HomeScreen(router: Router) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = "Home Screen",
            modifier = Modifier.align(Alignment.Center)
        )

        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BackButton(onClick = { router.back() })
            CloseButton(onClick = { println("Close clicked") })
            CameraContainer()
            GalleryContainer()
        }

        SheetContainer(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Text(
                    text = "Проверка контейнера",
                    style = MaterialTheme.typography.titleMedium,
                    color = TextTitle
                )

                Text(
                    text = "Протос куча рандомного текста просто куча рандомного текста " +
                            "Еще больше ранжоиноготекст сомти сомтри смоного смотлофрлыв",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )

                Text(
                    text = "кневпкы мавмпикт оньеглнбшлгь тонрипа цкпыеа епв",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
        }

        PrimaryButton(
            onClick = { println("Primary clicked") },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp)
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text("Test Button")
        }
    }
}