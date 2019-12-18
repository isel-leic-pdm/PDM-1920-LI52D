package org.isel.geniuz.lastfm.dto

class ArtistDto(
    val name: String,
    val listeners: Int,
    val mbid: String,
    val url: String,
    vararg val image: ImageDto
) {
    override fun toString(): String {
        return "ArtistDao(" +
                "\"$name\", " +
                "$listeners, " +
                "\"$mbid\" , " +
                "\"$url\", " +
                "${image.contentToString()})"
    }
}
