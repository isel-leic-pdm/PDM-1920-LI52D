package org.isel.geniuz

import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.volley.VolleyError
import org.geniuz.lastfm.LastfmWebApi
import org.isel.geniuz.lastfm.dto.AlbumDto
import org.isel.geniuz.lastfm.dto.GetAlbumsDto
import org.isel.geniuz.lastfm.dto.SearchDto

class AlbumsViewModel(private val lastfm: LastfmWebApi) : ViewModel() {
    var albums : Array<AlbumDto> = emptyArray()
        private set
    var mbid : String? = null

    fun getAlbums(
        mbid: String,
        onSuccess: (GetAlbumsDto) -> Unit,
        onError: (VolleyError) -> Unit)
    {
        if(mbid == this.mbid) return
        Log.v(TAG, "**** FETCHING Albums $mbid from Last.fm...")
        lastfm.getAlbums(mbid, 1, {albums ->
            Log.v(TAG, "**** FETCHING Albums $mbid COMPLETED !!!!")
            this.mbid = mbid
            this.albums = albums.topalbums.album
            onSuccess(albums)
        }, onError)
    }
}