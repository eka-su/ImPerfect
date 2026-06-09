package com.example.imperfect.core.database.seed

import com.example.imperfect.core.database.dao.LookupDao
import com.example.imperfect.core.database.entity.PhotoAnalysisStatusEntity
import com.example.imperfect.core.database.entity.PhotoViewTypeEntity

class LookupSeeder(
    private val dao: LookupDao
) {

    suspend fun seed() {

        dao.insertViewTypes(
            listOf(
                PhotoViewTypeEntity(
                    id = 1,
                    code = "FRONT",
                    name = "Front view"
                ),
                PhotoViewTypeEntity(
                    id = 2,
                    code = "LEFT",
                    name = "Left view"
                ),
                PhotoViewTypeEntity(
                    id = 3,
                    code = "RIGHT",
                    name = "Right view"
                )
            )
        )

        dao.insertPhotoAnalysisStatuses(
            listOf(
                PhotoAnalysisStatusEntity(
                    id = 1,
                    code = "PENDING",
                    name = "Pending"
                ),
                PhotoAnalysisStatusEntity(
                    id = 2,
                    code = "COMPLETED",
                    name = "Completed"
                ),
                PhotoAnalysisStatusEntity(
                    id = 3,
                    code = "FAILED",
                    name = "Failed"
                )
            )
        )
    }
}