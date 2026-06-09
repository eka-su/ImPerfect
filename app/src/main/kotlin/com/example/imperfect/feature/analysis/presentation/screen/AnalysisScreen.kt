package com.example.imperfect.feature.analysis.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.imperfect.R
import com.example.imperfect.core.navigation.Router
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.kit.list.CloseTextRow
import com.example.imperfect.feature.analysis.presentation.component.AcneSummaryCard
import com.example.imperfect.feature.analysis.presentation.component.AcneTypeCard
import com.example.imperfect.feature.analysis.presentation.component.MakeupSettingsSection
import com.example.imperfect.feature.analysis.presentation.component.AnalysisExtendedDescriptionCard
import com.example.imperfect.feature.analysis.presentation.component.AnalysisPhotoInfoCard
import com.example.imperfect.feature.analysis.presentation.component.AnalysisPhotoPager
import com.example.imperfect.feature.analysis.presentation.component.ContextNoteSection
import com.example.imperfect.feature.analysis.presentation.component.SkinHealthCard
import com.example.imperfect.feature.analysis.presentation.event.AnalysisEvent
import com.example.imperfect.feature.analysis.presentation.mapper.acneTypeUi
import com.example.imperfect.feature.analysis.presentation.utils.getScanTime
import com.example.imperfect.feature.analysis.presentation.viewmodel.AnalysisViewModel

//нужно правмльно разделить логику, но пока оставляю так, тк обработку состояний фото не добавила нормально
//добавить норально сосняия анализа загрузки ошибок, сделать отдкльный экран, елси напримре он недоступен просто фото выводить

@Composable
fun AnalysisResultScreen(
    viewModel: AnalysisViewModel,
    router: Router,
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    var showEditDialog by remember { mutableStateOf(false) }

    val hasMakeup by viewModel.hasMakeup.collectAsState()
    val note by viewModel.note.collectAsState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                AnalysisEvent.OpenPhotoFlow -> router.openPhotoFlow()
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.start()
    }

    when {

        state.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = state.error ?: "Unknown error",
                    color = Color.Red
                )
            }
        }

        state.result != null -> {

            val result = state.result!!

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .pointerInput(Unit) {
                        detectTapGestures {
                            focusManager.clearFocus()
                        }
                    },
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                item {
                    Spacer(Modifier.height(20.dp))

                    CloseTextRow(
                        text = "Результаты анализа",
                        onClick = onBackClick
                    )
                }

                item {
                    AnalysisPhotoPager(
                        photos = state.photos,
                        detectionsPerPhoto = result.images.map { it.detections },
                        onEditConfirmed = {
                            viewModel.onEditRequested()
                        }
                    )
                }

                item {

                    val scanTime = getScanTime(state.photos)

                    AnalysisPhotoInfoCard(
                        scanTime = scanTime,
                        onOpenClick = {
                            // compare logic
                        }
                    )
                }

                item {
                    SkinHealthCard(result = result)
                }

                item {

                    val acneStats = result.images
                        .flatMap { it.detections }
                        .groupBy { it.classId }
                        .map { (classId, list) ->
                            val percent =
                                ((list.size.toFloat() / result.summary.totalDetections) * 100).toInt()
                            acneTypeUi(classId, percent)
                        }

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        acneStats.forEach {
                            AcneTypeCard(it)
                        }

                        Row(
                            modifier = Modifier
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(30.dp)
                                )
                                .clickable {
                                    router.openSkinTypesGuide()
                                }
                                .padding(horizontal = 14.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.ic_check),
                                contentDescription = null,
                                tint = BluePrimary,
                                modifier = Modifier.size(14.dp)
                            )

                            Spacer(Modifier.width(4.dp))

                            Text(
                                text = "Узнать больше",
                                color = BluePrimary,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }

                item {
                    AcneSummaryCard(result = result)
                }

                item {
                    AnalysisExtendedDescriptionCard(result)
                }

                item {
                    MakeupSettingsSection(
                        hasMakeup = hasMakeup,
                        onMakeupChange = viewModel::setMakeup
                    )
                }

                item {
                    ContextNoteSection(
                        note = note,
                        onNoteChange = viewModel::setNote
                    )
                }
            }
        }
    }
}



