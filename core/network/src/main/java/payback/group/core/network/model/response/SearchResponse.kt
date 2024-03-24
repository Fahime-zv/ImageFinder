package payback.group.core.network.model.response

import kotlinx.serialization.SerialName

data class SearchResponse(
    @SerialName("total") val total: Int,
    @SerialName("totalHits") val totalHits: Int,
    @SerialName("hits") val hits: List<Hit>,
) {
    data class Hit(
        @SerialName("id") val id: Int,
        @SerialName("pageURL") val pageURL: String,
        @SerialName("previewURL") val previewURL: String,
        @SerialName("largeImageURL") val largeImageURL: String,
        @SerialName("fullHDURL") val fullHDURL: String,
        @SerialName("likes") val likes: Int,
        @SerialName("downloads") val downloads: Int,
        @SerialName("comments") val comments: Int,
        @SerialName("tags") val tags: List<String>,
        @SerialName("user") val user: String,
        )
}