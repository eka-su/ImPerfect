package com.example.imperfect.feature.photo.domain.util

import com.example.imperfect.feature.photo.domain.model.Photo

fun List<Photo>.replaceByStep(photo: Photo): List<Photo> {
    return filter { it.viewTypeId != photo.viewTypeId } + photo
}