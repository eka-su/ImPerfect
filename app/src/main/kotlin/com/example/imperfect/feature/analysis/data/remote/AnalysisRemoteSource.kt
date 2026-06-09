package com.example.imperfect.feature.analysis.data.remote

import com.example.imperfect.feature.analysis.data.remote.dto.AnalyzeMultiResponse
import com.example.imperfect.feature.analysis.data.utils.toMultipart
import java.io.File

class AnalysisRemoteSource(
    private val api: AnalysisApi
) {

    suspend fun analyze(
        front: File,
        left: File,
        right: File
    ): AnalyzeMultiResponse {

        return api.analyzeMulti(
            front = front.toMultipart("front"),
            left = left.toMultipart("left"),
            right = right.toMultipart("right")
        )
    }
}