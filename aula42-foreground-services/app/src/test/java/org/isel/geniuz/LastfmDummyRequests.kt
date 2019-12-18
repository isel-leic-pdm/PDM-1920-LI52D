package org.isel.geniuz

import com.google.gson.Gson
import org.geniuz.lastfm.LASTFM_GET_ALBUMS
import org.geniuz.lastfm.LASTFM_SEARCH
import org.isel.geniuz.lastfm.dto.GetAlbumsDto
import org.isel.geniuz.lastfm.dto.SearchDto
import org.junit.Test
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class LastfmDummyRequests {
    @Test
    fun testFetchArtistsMuse() {
        val dto = fetchArtistsMuse("muse", 1)
        dto.results.artistMatches.artist.forEach(::println)
    }
    @Test
    fun testFetchAlbums() {
        val artists = fetchArtistsMuse("muse", 1)
        artists.results.artistMatches.artist
            .map { fetchAlbum(it.mbid, 1) }
            .filter { it.topalbums != null }
            .forEach { it.topalbums.album.take(5).forEach(::println) }
    }

    fun fetchArtistsMuse(name: String, page: Int) : SearchDto {
        val url = String.format(LASTFM_SEARCH, name, page)
        val connection = URL(url).openStream()
        val reader = BufferedReader(InputStreamReader(connection))
        val gson = Gson()
        return gson.fromJson<SearchDto>(reader, SearchDto::class.java)
    }
    fun fetchAlbum(mbid: String, page: Int) : GetAlbumsDto {
        val url = String.format(LASTFM_GET_ALBUMS, mbid, page)
        val connection = URL(url).openStream()
        val reader = BufferedReader(InputStreamReader(connection))
        val gson = Gson()
        return gson.fromJson<GetAlbumsDto>(reader, GetAlbumsDto::class.java)
    }
}
