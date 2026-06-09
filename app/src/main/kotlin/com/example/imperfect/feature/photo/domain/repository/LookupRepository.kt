package com.example.imperfect.feature.photo.domain.repository

import com.example.imperfect.feature.photo.domain.model.PhotoViewType

interface LookupRepository {

    suspend fun getPhotoTypes(): List<PhotoViewType>
}