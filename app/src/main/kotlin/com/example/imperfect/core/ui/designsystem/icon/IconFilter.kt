package com.example.imperfect.core.ui.designsystem.icon

import com.example.imperfect.R

sealed class IconFilter(
    override val iconRes: Int,
    override val contentDescription: String
) : AppIcon {

    data object AllZones : IconFilter(
        R.drawable.ic_all_face,
        "All"
    )

    data object Front2 : IconFilter(
        R.drawable.ic_front2,
        "Front"
    )

    data object Left2 : IconFilter(
        R.drawable.ic_left2,
        "Left"
    )

    data object Right2 : IconFilter(
        R.drawable.ic_right2,
        "Right"
    )

    data object Blackheads : IconFilter(
        R.drawable.ic_blackheads,
        "Blackheads"
    )

    data object DarkSpots : IconFilter(
        R.drawable.ic_dark_spots,
        "Dark Spots"
    )

    data object Nodules : IconFilter(
        R.drawable.ic_nodules,
        "Nodules"
    )

    data object Papules : IconFilter(
        R.drawable.ic_papules,
        "Papules"
    )

    data object Pustules : IconFilter(
        R.drawable.ic_pustules,
        "Pustules"
    )

    data object Whiteheads : IconFilter(
        R.drawable.ic_whiteheads,
        "Whiteheads"
    )

    data object QuestionMan  : IconFilter(
        R.drawable.ic_question_man,
        "Question Man"
    )

    //уход категории
    data object Lotion : IconFilter(
        R.drawable.ic_lotion,
        "Лосьон"
    )

    data object Cream : IconFilter(
        R.drawable.ic_cream,
        "Крем"
    )

    data object Balm : IconFilter(
        R.drawable.ic_balm,
        "Бальзам"
    )

    data object Mask : IconFilter(
        R.drawable.ic_mask,
        "Маска"
    )

    data object Toner : IconFilter(
        R.drawable.ic_toner,
        "Тоник"
    )

    data object Gel : IconFilter(
        R.drawable.ic_gel,
        "Гель"
    )

    data object Emulsion : IconFilter(
        R.drawable.ic_emulsion,
        "Эмульсия"
    )

    data object Serum : IconFilter(
        R.drawable.ic_serum,
        "Сыворотка"
    )

    data object OtherCare : IconFilter(
        R.drawable.ic_other_care,
        "Другой уход"
    )

    // Утро ночь все
    data object Morning : IconFilter(
        R.drawable.ic_morning,
        "Утро"
    )

    data object Evening : IconFilter(
        R.drawable.ic_evening,
        "Вечер"
    )

    data object Alltime : IconFilter(
        R.drawable.ic_all_time,
        "Утром и вечер"
    )

    //СОСТОЯНИЕ КОЖИ (SKIN)
    data object DrySkin : IconFilter(
        R.drawable.ic_dry_skin,
        "Сухость"
    )

    data object OilySkin : IconFilter(
        R.drawable.ic_oily_skin,
        "Жирность"
    )

    data object Redness : IconFilter(
        R.drawable.ic_redness,
        "Покраснение"
    )

    data object Tightness : IconFilter(
        R.drawable.ic_tightness,
        "Стянутость"
    )

    data object Itching : IconFilter(
        R.drawable.ic_itching,
        "Зуд"
    )

    data object Peeling : IconFilter(
        R.drawable.ic_peeling,
        "Шелушение"
    )

    data object Sensitive : IconFilter(
        R.drawable.ic_sensitive,
        "Чувствительность"
    )

    data object Dehydrated : IconFilter(
        R.drawable.ic_dehydrated,
        "Обезвоженность"
    )

    data object Irritation : IconFilter(
        R.drawable.ic_irritation,
        "Раздражение"
    )

    data object Burning : IconFilter(
        R.drawable.ic_burning,
        "Жжение"
    )

    data object Tingling : IconFilter(
        R.drawable.ic_tingling,
        "Покалывание"
    )

    data object Dull : IconFilter(
        R.drawable.ic_dull,
        "Тусклость"
    )

    data object Shiny : IconFilter(
        R.drawable.ic_shiny,
        "Жирный блеск"
    )

    // САМОЧУВСТВИЕ (HEALTH)
    data object Amazing : IconFilter(
        R.drawable.ic_amazing,
        "Отлично"
    )

    data object Good : IconFilter(
        R.drawable.ic_good,
        "Хорошо"
    )

    data object Okay : IconFilter(
        R.drawable.ic_okay,
        "Средне"
    )

    data object Bad : IconFilter(
        R.drawable.ic_bad,
        "Плохо"
    )

    data object Happy : IconFilter(
        R.drawable.ic_happy,
        "Радость"
    )

    data object Stress : IconFilter(
        R.drawable.ic_stress,
        "Стресс"
    )

    data object Tired : IconFilter(
        R.drawable.ic_tired,
        "Усталость"
    )

    data object Sleepy : IconFilter(
        R.drawable.ic_sleepy,
        "Сонливость"
    )

    data object Headache : IconFilter(
        R.drawable.ic_headache,
        "Головная боль"
    )

    data object StomachAche : IconFilter(
        R.drawable.ic_stomach_ache,
        "Боли в животе"
    )

    data object Nausea : IconFilter(
        R.drawable.ic_nausea,
        "Тошнота"
    )

    data object Dizziness : IconFilter(
        R.drawable.ic_dizziness,
        "Головокружение"
    )

    data object Allergy : IconFilter(
        R.drawable.ic_allergy,
        "Аллергия"
    )

    data object Cold : IconFilter(
        R.drawable.ic_cold,
        "Простуда"
    )

    data object Bloating : IconFilter(
        R.drawable.ic_bloating,
        "Вздутие"
    )

    data object MusclePain : IconFilter(
        R.drawable.ic_muscle_pain,
        "Боли в мышцах"
    )

    data object Irritated : IconFilter(
        R.drawable.ic_irritated,
        "Раздражительность"
    )

    data object Apathy : IconFilter(
        R.drawable.ic_apathy,
        "Апатия"
    )

    data object Energetic : IconFilter(
        R.drawable.ic_energetic,
        "Энергичность"
    )

    data object Calm : IconFilter(
        R.drawable.ic_calm,
        "Спокойствие"
    )

    //ДЕФОЛТ
    data object Default : IconFilter(
        R.drawable.ic_default_feeling,
        "Не выбрано"
    )

}