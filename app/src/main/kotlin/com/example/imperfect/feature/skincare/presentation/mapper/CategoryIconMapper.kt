package com.example.imperfect.feature.skincare.presentation.mapper

import com.example.imperfect.core.ui.designsystem.icon.IconFilter

object CategoryIconMapper {

    fun iconFor(code: String): IconFilter {
        return when (code) {

            "LOTION" -> IconFilter.Lotion
            "CREAM" -> IconFilter.Cream
            "BALM" -> IconFilter.Balm
            "MASK" -> IconFilter.Mask
            "TONER" -> IconFilter.Toner
            "GEL" -> IconFilter.Gel
            "EMULSION" -> IconFilter.Emulsion
            "SERUM" -> IconFilter.Serum
            else -> IconFilter.OtherCare
        }
    }
}