package com.example.imperfect.core.ui.theme

import androidx.compose.ui.graphics.Color

// TEXT
val TextPrimary = Color(0xFF000000) // главные заголоввки
val TextTitle = Color(0xFF232323) // заголовки помягче
val TextSecondary = Color(0xFF606060) // описания, подписи

// ICONS, неактивные
val IconGray = Color(0xFFB8B8B8)

//BRAND, основные цвета приложения
val BluePrimary = Color(0xFF196EEE) //главный
val BlueSecondary = Color(0xFF176FF2) //вторичный
val BlueShadow = Color(0xFF186FF2) //для теней
val BlueLightBackground = BlueSecondary.copy(alpha = 0.05f) //фон под иконками
val BlueShadowLight = BlueShadow.copy(alpha = 0.05f) //мягкая тень голубая
val GreyShadow = Color(0xFF484848) //для теней
val GreyShadowLight = GreyShadow.copy(alpha = 0.46f) // мягая тень серая, для карточек
val BlackShadowLight = TextPrimary.copy(alpha = 0.25f) // мягкая тень черная, кнопки
val BottomBarGradientStart = Color(0xFFF5F5F5) //для градиента бара

// ACCENT, акцентные цвета, что - то декаративное
val Red = Color(0xFFEC5655)
val Green = Color(0xFF2DD7A4)
val Yellow = Color(0xFFFFCB2B)

// BACKGROUND / SURFACE
val InputBackground = Color(0xFFF3F8FE) //поля ввода, формы
val Background = Color(0xFFFFFFFF) //фон