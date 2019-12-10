package org.isel.geniuz.lastfm

import com.google.gson.Gson
import org.geniuz.lastfm.LASTFM_GET_ALBUMS
import org.geniuz.lastfm.LASTFM_SEARCH
import org.isel.geniuz.lastfm.dto.GetAlbumsDto
import org.isel.geniuz.lastfm.dto.SearchDto
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

/**
 * !!! NãO usar em Android. Apenas em testes unitários se necessário !!!!
 */
class LastfmWebApiBlocking {

    fun searchArtist(name: String, page: Int) : SearchDto {
        val url = String.format(LASTFM_SEARCH, name, page)
        val connection = URL(url).openStream()
        val reader = BufferedReader(InputStreamReader(connection))
        val gson = Gson()
        return gson.fromJson<SearchDto>(reader, SearchDto::class.java)
    }

    fun getAalbums(mbid: String, page: Int) : GetAlbumsDto {
        val url = String.format(LASTFM_GET_ALBUMS, mbid, page)
        val connection = URL(url).openStream()
        val reader = BufferedReader(InputStreamReader(connection))
        val gson = Gson()
        return gson.fromJson<GetAlbumsDto>(reader, GetAlbumsDto::class.java)
    }
}