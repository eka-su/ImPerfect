package com.example.imperfect.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.imperfect.R

// используемые сейчас
val Montserrat = FontFamily(
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_bold, FontWeight.Bold)
)

val Inter = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_light, FontWeight.Light),
    Font(R.font.inter_bold, FontWeight.Bold)
)


// дополнительные (пока не используются)
val MontserratExtra = FontFamily(
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_medium, FontWeight.Medium)
)

val InterExtra = FontFamily(
    Font(R.font.inter_semibold, FontWeight.SemiBold)
)

object LineHeights {
    val Default = 20.sp
    val Small = 16.sp
}

val Typography = Typography(

    // Главный, верхний заголовок
    titleLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = LineHeights.Default
    ),

    //Подзаголовки разделов
    titleMedium = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = LineHeights.Default
    ),

    // Меньшие подзаголовки, 1/5 заполнено
    titleSmall = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = LineHeights.Default
    ),

    // Альтернативные заголовки
    labelMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = LineHeights.Small
    ),

    // Описания
    bodyLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = LineHeights.Default
    ),

    // Объемные описания
    bodyMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        lineHeight = LineHeights.Default
    ),

    // Небольшие описания, ввод в полях
    bodySmall = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = LineHeights.Default
    ),

    // Кнопки
    labelLarge = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = LineHeights.Default
    ),

    // Больше, раскрыть
    labelSmall = TextStyle(
        fontFamily = Montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = LineHeights.Default
    ),

    // Подписи иконок, что то мелкое
    displaySmall = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Light,
        fontSize = 10.sp,
        lineHeight = LineHeights.Default
    ),

    // Инсайт, что-то важное
    displayMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = LineHeights.Default
    )
)