package com.example.imperfect.feature.photo.data.source

import com.example.imperfect.core.database.dao.LookupDao
import com.example.imperfect.core.database.entity.PhotoViewTypeEntity

class LookupLocalSource(
    private val dao: LookupDao
) {
    suspend fun getPhotoTypes(): List<PhotoViewTypeEntity> {
        return dao.getViewTypes()
    }
}