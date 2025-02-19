package com.trycatch.prography.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoEntity(
    val width: Int,
    val height: Int,
    private val description: String?,
    @SerialName("alt_description")
    private val altDescription: String?,
    @SerialName("urls")
    private val urls: PhotoUrl
) {
    val url get() = urls.raw
    val title get() = description ?: "$altDescription"
}

@Serializable
data class PhotoUrl(
    val raw: String
)
