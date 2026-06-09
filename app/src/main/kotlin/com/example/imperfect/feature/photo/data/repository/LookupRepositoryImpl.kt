package com.example.imperfect.feature.photo.data.repository

import com.example.imperfect.feature.photo.data.mapper.toDomain
import com.example.imperfect.feature.photo.data.source.LookupLocalSource
import com.example.imperfect.feature.photo.domain.model.PhotoViewType
import com.example.imperfect.feature.photo.domain.repository.LookupRepository

class LookupRepositoryImpl(
    private val localSource: LookupLocalSource
) : LookupRepository {

    override suspend fun getPhotoTypes(): List<PhotoViewType> {
        return localSource
            .getPhotoTypes()
            .map { it.toDomain() }
    }
}