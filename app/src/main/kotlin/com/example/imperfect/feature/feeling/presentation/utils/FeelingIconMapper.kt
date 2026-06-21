package com.example.imperfect.feature.feeling.presentation.utils

import com.example.imperfect.core.ui.designsystem.icon.IconFilter

object FeelingIconMapper {

    fun getIcon(name: String): IconFilter {
        return when (name) {
            //Состояние кожи
            "Сухость" -> IconFilter.DrySkin
            "Жирность" -> IconFilter.OilySkin
            "Покраснение" -> IconFilter.Redness
            "Стянутость" -> IconFilter.Tightness
            "Зуд" -> IconFilter.Itching
            "Шелушение" -> IconFilter.Peeling
            "Чувствительность" -> IconFilter.Sensitive
            "Обезвоженность" -> IconFilter.Dehydrated
            "Раздражение" -> IconFilter.Irritation
            "Жжение" -> IconFilter.Burning
            "Покалывание" -> IconFilter.Tingling
            "Тусклость" -> IconFilter.Dull
            "Жирный блеск" -> IconFilter.Shiny

            //Самочувствие
            "Отлично" -> IconFilter.Amazing
            "Хорошо" -> IconFilter.Good
            "Средне" -> IconFilter.Okay
            "Плохо" -> IconFilter.Bad
            "Радость" -> IconFilter.Happy
            "Стресс" -> IconFilter.Stress
            "Усталость" -> IconFilter.Tired
            "Сонливость" -> IconFilter.Sleepy
            "Головная боль" -> IconFilter.Headache
            "Боли в животе" -> IconFilter.StomachAche
            "Тошнота" -> IconFilter.Nausea
            "Головокружение" -> IconFilter.Dizziness
            "Аллергия" -> IconFilter.Allergy
            "Простуда" -> IconFilter.Cold
            "Вздутие" -> IconFilter.Bloating
            "Боли в мышцах" -> IconFilter.MusclePain
            "Раздражительность" -> IconFilter.Irritated
            "Апатия" -> IconFilter.Apathy
            "Энергичность" -> IconFilter.Energetic
            "Спокойствие" -> IconFilter.Calm

            else -> IconFilter.Default
        }
    }
}