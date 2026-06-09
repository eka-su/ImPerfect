package com.example.imperfect.feature.analysis.domain.interpretation

import com.example.imperfect.feature.analysis.domain.model.AnalysisResult
import com.example.imperfect.feature.analysis.presentation.mapper.acneTypeUi
import kotlin.math.abs

fun buildExtendedDescription(result: AnalysisResult): String {

    val detections = result.images.flatMap { it.detections }
    val grouped = detections.groupBy { it.classId }

    val topEntry = grouped.maxByOrNull { it.value.size }
    val topClassId = topEntry?.key ?: 0
    val topPercent = if (detections.isNotEmpty()) {
        topEntry?.value?.size?.times(100)?.div(detections.size) ?: 0
    } else 0

    val topType = acneTypeUi(topClassId, 0).name

    val frontCount = result.images.getOrNull(0)?.detectedCount ?: 0
    val leftCount = result.images.getOrNull(1)?.detectedCount ?: 0
    val rightCount = result.images.getOrNull(2)?.detectedCount ?: 0

    val maxCount = maxOf(frontCount, leftCount, rightCount)

    val worstZone = when {
        maxCount == frontCount -> "фронтальной зоне"
        maxCount == leftCount -> "левой стороне лица"
        else -> "правой стороне лица"
    }

    val affectedZones = listOf(frontCount, leftCount, rightCount).count { it > 0 }

    val spreadText = when (affectedZones) {
        1 -> "локализованы в одной зоне лица"
        2 -> "затрагивают две зоны лица"
        3 -> "наблюдаются на всех анализируемых участках лица"
        else -> "не определены"
    }

    val activeIds = setOf(2, 3, 4)
    val activeCount = detections.count { it.classId in activeIds }

    val activityText = when {
        detections.isEmpty() -> "признаки воспалительных изменений не выявлены"
        activeCount > detections.size * 0.6 -> "активной воспалительной стадии"
        activeCount > detections.size * 0.3 -> "умеренной воспалительной стадии"
        else -> "преимущественно постакне и невоспалительным изменениям"
    }

    val diff = abs(leftCount - rightCount)
    val averageSide = (leftCount + rightCount) / 2.0

    val symmetryText = when {
        averageSide == 0.0 ->
            "На боковых сторонах лица выраженных высыпаний не обнаружено."

        diff <= averageSide * 0.2 ->
            "Распределение высыпаний между правой и левой сторонами лица является симметричным."

        leftCount > rightCount ->
            "На левой стороне лица отмечается больше высыпаний по сравнению с правой."

        else ->
            "На правой стороне лица отмечается больше высыпаний по сравнению с левой."
    }

    val confidence = (result.summary.averageConfidence * 100).toInt()

    fun acneWord(count: Int): String {
        val mod100 = count % 100
        val mod10 = count % 10

        return when {
            mod100 in 11..14 -> "высыпаний"
            mod10 == 1 -> "высыпание"
            mod10 in 2..4 -> "высыпания"
            else -> "высыпаний"
        }
    }

    return """
    В ходе анализа кожи лица было обнаружено ${result.summary.totalDetections} ${acneWord(result.summary.totalDetections)} акне.
    
    На фронтальной зоне выявлено $frontCount ${acneWord(frontCount)}, 
    на левой стороне лица — $leftCount ${acneWord(leftCount)}, 
    на правой стороне — $rightCount ${acneWord(rightCount)}. 
    Наиболее выраженные изменения наблюдаются в $worstZone.
    
    Преобладающий тип высыпаний — $topType, которые составляет около $topPercent% от общего количества обнаружений. 
    В целом высыпания $spreadText.
    
    По характеру обнаруженных элементов состояние кожи можно отнести к $activityText. 
    $symmetryText
    
    Средняя уверенность модели в распознавании составляет $confidence%.
    Рекомендуется отслеживать динамику состояния кожи и при необходимости проконсультироваться с врачом-дерматологом.
""".trimIndent()


}