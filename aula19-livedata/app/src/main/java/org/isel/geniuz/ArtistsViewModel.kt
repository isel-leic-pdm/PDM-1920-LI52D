package org.isel.geniuz

import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.volley.VolleyError
import kotlinx.android.synthetic.main.activity_main.*
import org.geniuz.lastfm.LastfmWebApi
import org.isel.geniuz.lastfm.dto.ArtistDto
import org.isel.geniuz.lastfm.dto.SearchDto

class ArtistsViewModel(private val lastfm: LastfmWebApi) : ViewModel() {
    var artists : Array<ArtistDto> = emptyArray()
        private set

    fun searchArtist(
        name: String,
        onSuccess: (SearchDto) -> Unit,
        onError: (VolleyError) -> Unit)
    {
        Log.v(TAG, "**** FETCHING Artists $name from Last.fm...")
        lastfm.searchArtist(name, 1, {artists ->
            Log.v(TAG, "**** FETCHING Artists $name COMPLETED !!!!")
            this.artists = artists.results.artistMatches.artist
            onSuccess(artists)
        }, onError)
    }
}