package com.example.imperfect.feature.skincare.presentation.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.example.imperfect.core.ui.designsystem.theme.Background
import com.example.imperfect.core.ui.designsystem.theme.BlueLightBackground
import com.example.imperfect.core.ui.designsystem.theme.BluePrimary
import com.example.imperfect.core.ui.designsystem.theme.InputBackground
import com.example.imperfect.core.ui.designsystem.theme.Red
import com.example.imperfect.core.ui.designsystem.theme.TextSecondary
import com.example.imperfect.core.ui.kit.button.PrimaryButton
import com.example.imperfect.core.ui.kit.chips.FlexibleChip
import com.example.imperfect.core.ui.kit.container.InputContainer
import com.example.imperfect.core.ui.kit.dialog.photosourcesheet.PhotoSourceSheet
import com.example.imperfect.core.ui.kit.icon.clickable.DeleteButton
import com.example.imperfect.core.ui.kit.list.BackTextRow
import com.example.imperfect.feature.skincare.domain.model.CareProduct
import com.example.imperfect.feature.skincare.presentation.mapper.CategoryIconMapper
import com.example.imperfect.feature.skincare.presentation.state.SkincareUiState
import java.io.File
import java.time.LocalDate

@Composable
fun CreateProductScreen(
    state: SkincareUiState,
    editingProduct: CareProduct? = null,
    onBack: () -> Unit,
    onSave: (
        brand: String,
        name: String,
        description: String,
        categoryId: Int?,
        imagePath: String?
    ) -> Unit,
    onSelectCategory: (Int) -> Unit,
    onSelectImage: (String?) -> Unit,
    onDelete: (() -> Unit)? = null
) {

    // STATE
    var brand by remember(editingProduct) { mutableStateOf(editingProduct?.brand ?: "") }
    var name by remember(editingProduct) { mutableStateOf(editingProduct?.name ?: "") }
    var description by remember(editingProduct) { mutableStateOf(editingProduct?.description ?: "") }
    var imagePath by remember(editingProduct) { mutableStateOf(editingProduct?.imagePath) }

    var nameError by remember { mutableStateOf<String?>(null) }
    var showPhotoSheet by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    // IMAGE PICKERS
    val context = LocalContext.current

    val file = remember {
        File(context.cacheDir, "photo_${System.currentTimeMillis()}.jpg")
    }

    val uri = remember(file) {
        FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
    }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { result ->
            result?.let { imagePath = it.toString() }
        }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) imagePath = uri.toString()
        }

    //ROOT
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus(force = true)
                    keyboardController?.hide()
                }
            }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            HeaderSection(
                editingProduct = editingProduct,
                onBack = onBack,
                onDelete = onDelete
            )

            Spacer(Modifier.height(24.dp))

            BrandField(brand) { brand = it }
            Spacer(Modifier.height(16.dp))

            NameField(name) { name = it }
            Spacer(Modifier.height(16.dp))

            DescriptionField(description) { description = it }

            Spacer(Modifier.height(24.dp))

            CategorySection(
                state = state,
                onSelectCategory = onSelectCategory
            )

            Spacer(Modifier.height(16.dp))

            PhotoSection(
                imagePath = imagePath,
                showPhotoSheet = showPhotoSheet,
                onShowSheet = { showPhotoSheet = it },
                cameraLauncher = cameraLauncher,
                galleryLauncher = galleryLauncher
            )

            Spacer(Modifier.height(16.dp))

            DateSection()

            Spacer(Modifier.height(16.dp))

            SaveSection(
                name = name,
                brand = brand,
                description = description,
                state = state,
                imagePath = imagePath,
                onSave = onSave,
                onError = { nameError = it },
                clearError = { nameError = null }
            )

            if (nameError != null) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = nameError!!,
                    color = Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
private fun HeaderSection(
    editingProduct: CareProduct?,
    onBack: () -> Unit,
    onDelete: (() -> Unit)?
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        BackTextRow(
            text = if (editingProduct != null)
                "Редактировать продукт"
            else
                "Создать продукт",
            onClick = onBack,
            modifier = Modifier.weight(1f)
        )

        if (editingProduct != null) {
            DeleteButton(onClick = { onDelete?.invoke() })
        }
    }
}

//FIELDS
@Composable
private fun BrandField(value: String, onChange: (String) -> Unit) {
    Text("Название бренда")
    Spacer(Modifier.height(8.dp))

    InputContainer(
        value = value,
        onValueChange = onChange,
        placeholder = "Введите бренд",
        modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences
        )
    )
}

@Composable
private fun NameField(value: String, onChange: (String) -> Unit) {
    Text("Название продукта")
    Spacer(Modifier.height(8.dp))

    InputContainer(
        value = value,
        onValueChange = onChange,
        placeholder = "Введите название",
        modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences
        )
    )
}

@Composable
private fun DescriptionField(value: String, onChange: (String) -> Unit) {
    Text("Описание")
    Spacer(Modifier.height(8.dp))

    InputContainer(
        value = value,
        onValueChange = onChange,
        placeholder = "Описание",
        modifier = Modifier.fillMaxWidth().heightIn(min = 56.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences
        )
    )
}



@Composable
private fun CategorySection(
    state: SkincareUiState,
    onSelectCategory: (Int) -> Unit
) {
    Text("Категория")
    Spacer(Modifier.height(12.dp))

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        state.categories.forEach { category ->
            val isSelected = state.selectedCategoryId == category.id
            val icon = CategoryIconMapper.iconFor(category.code)

            FlexibleChip(
                modifier = Modifier.clickable {
                    onSelectCategory(category.id)
                },
                backgroundColor = if (isSelected)
                    BluePrimary.copy(alpha = 0.15f)
                else
                    BlueLightBackground
            ) {
                Icon(
                    painter = painterResource(id = icon.iconRes),
                    contentDescription = icon.contentDescription,
                    tint = if (isSelected) BluePrimary else TextSecondary,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = category.name,
                    color = if (isSelected) BluePrimary else TextSecondary
                )
            }
        }
    }
}

@Composable
private fun PhotoSection(
    imagePath: String?,
    showPhotoSheet: Boolean,
    onShowSheet: (Boolean) -> Unit,
    cameraLauncher: androidx.activity.result.ActivityResultLauncher<android.net.Uri>,
    galleryLauncher: androidx.activity.result.ActivityResultLauncher<String>
) {

    if (showPhotoSheet) {
        PhotoSourceSheet(
            onCameraClick = {
                onShowSheet(false)
                cameraLauncher.launch(imagePath?.let { Uri.parse(it) } ?: Uri.EMPTY)
            },
            onGalleryClick = {
                onShowSheet(false)
                galleryLauncher.launch("image/*")
            },
            onClose = { onShowSheet(false) }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(227.dp)
            .background(InputBackground, RoundedCornerShape(16.dp))
            .clickable { onShowSheet(true) },
        contentAlignment = Alignment.Center
    ) {
        if (imagePath != null) {
            AsyncImage(
                model = imagePath,
                contentDescription = null,
                modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
        } else {
            Text(
                text = "Нажмите, чтобы добавить фото",
                color = TextSecondary,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun DateSection() {
    Text("Дата создания")

    Spacer(Modifier.height(8.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(InputBackground, RoundedCornerShape(16.dp))
            .padding(14.dp)
    ) {
        Text(
            text = LocalDate.now().toString(),
            color = TextSecondary,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun SaveSection(
    name: String,
    brand: String,
    description: String,
    state: SkincareUiState,
    imagePath: String?,
    onSave: (
        String,
        String,
        String,
        Int?,
        String?
    ) -> Unit,
    onError: (String) -> Unit,
    clearError: () -> Unit
) {

    PrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            if (name.isBlank()) {
                onError("Введите название продукта")
                return@PrimaryButton
            }

            clearError()

            onSave(
                brand,
                name,
                description,
                state.selectedCategoryId,
                imagePath
            )
        }
    ) {
        Text("Сохранить")
    }
}
