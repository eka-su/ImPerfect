package com.example.imperfect.feature.analysis.data.utils

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

fun File.toMultipart(
    partName: String
): MultipartBody.Part {

    val body = asRequestBody(
        "image/*".toMediaType()
    )

    return MultipartBody.Part.createFormData(
        partName,
        name,
        body
    )
}