package org.geniuz.lastfm.dto

data class ArtistDto(
    val name: String,
    val listeners: Int,
    val mbid: String,
    val url: String,
    val image: Array<ImageDto>
)
