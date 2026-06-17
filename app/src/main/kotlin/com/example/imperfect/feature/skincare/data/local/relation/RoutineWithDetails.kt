package com.example.imperfect.feature.skincare.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.imperfect.core.database.entity.CareRoutineItemEntity
import com.example.imperfect.core.database.entity.SkincareProductEntity

data class RoutineWithDetails(

    @Embedded
    val routine: CareRoutineItemEntity,

    @Relation(
        parentColumn = "productId",
        entityColumn = "id"
    )
    val product: SkincareProductEntity,

    @Relation(
        entity = com.example.imperfect.core.database.entity.CareRoutineTimeLinkEntity::class,
        parentColumn = "id",
        entityColumn = "careItemId"
    )
    val times: List<CareRoutineTimeLinkWithSlot>
)