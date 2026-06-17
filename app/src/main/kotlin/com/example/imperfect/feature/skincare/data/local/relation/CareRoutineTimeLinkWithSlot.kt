package com.example.imperfect.feature.skincare.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.imperfect.core.database.entity.CareRoutineTimeLinkEntity
import com.example.imperfect.core.database.entity.CareTimeSlotEntity

data class CareRoutineTimeLinkWithSlot(

    @Embedded
    val link: CareRoutineTimeLinkEntity,

    @Relation(
        parentColumn = "timeId",
        entityColumn = "id"
    )
    val slot: CareTimeSlotEntity
)