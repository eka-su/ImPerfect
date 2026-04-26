package com.example.imperfect.core.navigation

// Проверяет, является ли текущий маршрут активным для данного экрана
fun Screen.isSelected(currentRoute: String?): Boolean {
    if (currentRoute == null) return false
    return currentRoute.substringBefore("/") == route.substringBefore("/{")
}