package com.example.imperfect.feature.analysis.data.remote

import com.example.imperfect.feature.analysis.data.remote.dto.AnalyzeMultiResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AnalysisApi {

    @Multipart
    @POST("/analyze-multi")
    suspend fun analyzeMulti(

        @Part front: MultipartBody.Part,

        @Part left: MultipartBody.Part,

        @Part right: MultipartBody.Part

    ): AnalyzeMultiResponse
}