package org.isel.geniuz.model

import androidx.lifecycle.LiveData
import org.isel.geniuz.GeniuzApp
import org.isel.geniuz.lastfm.dto.SearchDto

class ArtistRepository {

    fun findByName(name: String): LiveData<List<Artist>> {
        val res = GeniuzApp.db.artistDao().findByName("%$name%")
        checkArtists(res, name)
        return res
    }

    private fun checkArtists(
        res: LiveData<List<Artist>>,
        name: String
    ) {
        /*
         * TO DO: do not block on this validation !!!!
         */
        if(res.value != null && res.value!!.size != 0)
            return
        // Fetch data from Lastfm Web API
        GeniuzApp.lastfm.searchArtist(name, 1, {
            val artists = fromDto(it)
            GeniuzApp.db.artistDao().insertAll(*artists)
        }, {throw it})
    }

    private fun fromDto(dto: SearchDto): Array<out Artist> =
        dto
            .results
            .artistMatches
            .artist
            .map { Artist(it.mbid, it.name, it.url, it.image[0].uri) }
            .toTypedArray()
}

