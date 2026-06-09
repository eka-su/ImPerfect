package com.example.imperfect.feature.analysis.data.source

import android.util.Log
import com.example.imperfect.core.database.dao.AnalysisContextDao
import com.example.imperfect.core.database.entity.AnalysisContextEntity

class AnalysisContextLocalSource(
    private val dao: AnalysisContextDao
) {
    private val TAG = "CTX_SOURCE"

    suspend fun save(
        multiId: Int,
        hasMakeup: Boolean,
        note: String?
    ) {
        Log.d(TAG, "SAVE CONTEXT START")
        Log.d(TAG, "multiId = $multiId")
        Log.d(TAG, "hasMakeup = $hasMakeup")
        Log.d(TAG, "note = $note")

        try {
            val entity = AnalysisContextEntity(
                multi_analysis_id = multiId,
                has_makeup = if (hasMakeup) 1 else 0,
                note = note
            )

            Log.d(TAG, "ENTITY = $entity")

            dao.insert(entity)

            Log.d(TAG, "SAVE CONTEXT SUCCESS")

        } catch (e: Exception) {
            Log.e(TAG, "SAVE CONTEXT CRASH", e)
            throw e
        }
    }

    suspend fun get(multiId: Int): AnalysisContextEntity? {
        Log.d(TAG, "GET CONTEXT START multiId=$multiId")

        return try {
            val result = dao.getByMultiId(multiId)

            Log.d(TAG, "GET CONTEXT RESULT = $result")

            result
        } catch (e: Exception) {
            Log.e(TAG, "GET CONTEXT CRASH", e)
            throw e
        }
    }
}