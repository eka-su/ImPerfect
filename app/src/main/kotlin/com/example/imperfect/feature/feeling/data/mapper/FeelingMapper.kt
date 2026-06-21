package com.example.imperfect.feature.feeling.data.mapper

import com.example.imperfect.core.database.entity.FeelingOptionEntity
import com.example.imperfect.core.database.entity.FeelingScreenEntity
import com.example.imperfect.feature.feeling.domain.model.FeelingForm
import com.example.imperfect.feature.feeling.domain.model.FeelingOption

object FeelingMapper {
    fun map(
        screen: FeelingScreenEntity,
        options: List<FeelingOptionEntity>,
        selectedIds: List<Int>,
        note: String
    ): FeelingForm {

        return FeelingForm(
            screenId = screen.id,
            title = screen.name,
            note = note,
            options = options.map {
                FeelingOption(
                    id = it.id,
                    name = it.name,
                    selected = selectedIds.contains(it.id)
                )
            }
        )
    }
}