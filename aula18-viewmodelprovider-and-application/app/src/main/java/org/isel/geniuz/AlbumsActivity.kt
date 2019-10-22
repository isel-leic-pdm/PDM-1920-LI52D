package org.isel.geniuz

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.geniuz.lastfm.LastfmWebApi

class AlbumsActivity : AppCompatActivity() {

    val lastfm: LastfmWebApi by lazy {
        LastfmWebApi(this)
    }
    val adapter : AlbumsAdapter by lazy {
        AlbumsAdapter(model)
    }
    val model : AlbumsViewModel by lazy {
        ViewModelProviders.of(this)[AlbumsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
        /**
         * Setup recyclerArtists with ArtistsAdapter
         */
        val mbid = intent.getStringExtra(ARTIST_MBID)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerAlbums)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        /**
         * Setup artis name on top of the screen
         */
        val name= intent.getStringExtra(ARTIST_NAME)
        findViewById<TextView>(R.id.txtAlbumsArtistName).text = name
        /**
         * Get albums from artist with mbid
         */
        Log.v(TAG, "**** FETCHING Albums $mbid from Last.fm...")
        lastfm.getAlbums(mbid, 1, {albums ->
            Log.v(TAG, "**** FETCHING Albums $mbid COMPLETED !!!!")
            model.albums = albums.topalbums.album
            adapter.notifyDataSetChanged()
        }, {err -> throw err})
    }
}
