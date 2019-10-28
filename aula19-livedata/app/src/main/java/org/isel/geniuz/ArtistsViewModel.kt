package org.isel.geniuz

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import org.geniuz.lastfm.LastfmWebApi
import org.isel.geniuz.lastfm.dto.ArtistDto

class ArtistsViewModel(private val lastfm: LastfmWebApi) : ViewModel() {
    private var liveData : MutableLiveData<Array<ArtistDto>> = MutableLiveData(emptyArray())

    val artists : Array<ArtistDto>
        get() = liveData.value!!

    fun searchArtist(name: String)
    {
        Log.v(TAG, "**** FETCHING Artists $name from Last.fm...")
        lastfm.searchArtist(name, 1, {artists ->
            Log.v(TAG, "**** FETCHING Artists $name COMPLETED !!!!")
            this.liveData.value = artists.results.artistMatches.artist
        }, { throw it })
    }

    fun observe(owner: LifecycleOwner, observer: (Array<ArtistDto>) -> Unit) {
        liveData.observe(owner, Observer {
            observer(it)
        })
    }
}