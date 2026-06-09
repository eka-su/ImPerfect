package com.example.imperfect.feature.analysis.data.mapper

import android.content.Context
import android.net.Uri
import java.io.File

fun Uri.toFile(
    context: Context
): File {

    val input = context.contentResolver
        .openInputStream(this)

    val file = File(
        context.cacheDir,
        "upload_${System.currentTimeMillis()}.jpg"
    )

    input.use { inputStream ->

        file.outputStream().use { output ->

            inputStream?.copyTo(output)
        }
    }

    return file
}