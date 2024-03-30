package payback.group.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("total") val total: Int,
    @SerialName("totalHits") val totalHits: Int,
    @SerialName("hits") val hits: List<Hit>,
) {

    @Serializable
    data class Hit(
        @SerialName("id") val id: Long,
        @SerialName("pageURL") val pageURL: String,
        @SerialName("previewURL") val previewURL: String,
        @SerialName("largeImageURL") val largeImageURL: String,
        @SerialName("likes") val likes: Int,
        @SerialName("downloads") val downloads: Int,
        @SerialName("comments") val comments: Int,
        @SerialName("tags") val tags: String,
        @SerialName("user") val user: String,
    )
}