package org.isel.geniuz.lastfm.dto

class AlbumDto(
    val name: String,
    val artist: ArtistDto,
    val playcount: Int,
    val mbid: String,
    val url: String,
    vararg val image: ImageDto,
    val tracks: TracksDto? = null
) {
    override fun toString(): String {
        return "AlbumDto(" +
                "\"$name\", " +
                "$artist, " +
                "$playcount, " +
                "\"$mbid\", " +
                "\"$url\", " +
                "${image.contentToString()}" +
                ")"
    }
}

class TracksDto(val track: Array<TrackDto>)
