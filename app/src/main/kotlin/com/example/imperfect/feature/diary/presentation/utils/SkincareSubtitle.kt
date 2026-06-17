package com.example.imperfect.feature.diary.presentation.utils

import com.example.imperfect.feature.diary.domain.model.DiaryFullUi

fun DiaryFullUi?.skincareSubtitle(): String {
    val list = this?.skincare ?: return "Добавь свои средства ухода"

    if (list.isEmpty()) return "Добавь свои средства ухода"

    fun short(name: String, max: Int): String =
        if (name.length <= max) name else name.take(max) + "..."

    val first = short(list.first().product.name, 27)

    return when {
        list.size == 1 ->
            first

        list.size == 2 ->
            "$first, ${short(list[1].product.name, 8)}"

        else ->
            "$first, ${short(list[1].product.name, 8)} +${list.size - 2}"
    }
}