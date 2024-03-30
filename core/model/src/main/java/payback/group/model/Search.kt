package payback.group.model

data class Search(
    val total: Int,
    val totalHits: Int,
    val hits: List<Hit>,
) {
    data class Hit(
        val id: Long,
        val pageURL: String,
        val previewURL: String,
        val largeImageURL: String,
        val likes: Int,
        val downloads: Int,
        val comments: Int,
        val tags: String,
        val user: String,
    )
}