package com.trycatch.prography.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoEntity(
    val id: String,
    val width: Int,
    val height: Int,
    private val description: String?,
    @SerialName("alt_description")
    private val altDescription: String?,
    @SerialName("urls")
    private val urls: PhotoUrl,
    @SerialName("tags")
    private val tagList: List<PhotoTag?> = emptyList(),
    private val user: User
) {
    val url get() = urls.raw
    val title get() = description ?: "$altDescription"
    val tags get() = tagList
        .mapNotNull {
            it?.title
        }.map {
            "#$it"
        }.joinToString(" ")
    val username get() = user.username
}

@Serializable
data class PhotoUrl(
    val raw: String
)

@Serializable
data class PhotoTag(
    val title: String
)

@Serializable
data class User(
    val username: String
)