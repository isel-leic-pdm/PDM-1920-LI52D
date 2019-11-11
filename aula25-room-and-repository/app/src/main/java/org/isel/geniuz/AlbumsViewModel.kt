package org.isel.geniuz

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.VolleyError
import org.geniuz.lastfm.LastfmWebApi
import org.isel.geniuz.lastfm.dto.AlbumDto
import org.isel.geniuz.lastfm.dto.GetAlbumsDto
import org.isel.geniuz.lastfm.dto.SearchDto

class AlbumsViewModel(private val lastfm: LastfmWebApi) : ViewModel() {
    var albums : MutableLiveData<Array<AlbumDto>> = MutableLiveData<Array<AlbumDto>>(emptyArray())
        private set
    var mbid : String? = null

    fun getAlbums(
        mbid: String)
    {
        if(mbid == this.mbid) return
        Log.v(TAG, "**** FETCHING Albums $mbid from Last.fm...")
        lastfm.getAlbums(mbid, 1, {albums ->
            Log.v(TAG, "**** FETCHING Albums $mbid COMPLETED !!!!")
            this.mbid = mbid
            /**
             * This callback is invoked from a Background thread!!!
             * Thus you cannot update UI otherwise it throws:
             *   IllegalStateException: Cannot invoke setValue on a background thread
             */
            // this.albums.value = albums.topalbums.album
            this.albums.postValue(albums.topalbums.album)
        }, {throw it})
    }
}