package com.trycatch.prography.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BookmarkEntity(
    val id: String,
    val width: Int,
    val height: Int,
    val url: String
) 