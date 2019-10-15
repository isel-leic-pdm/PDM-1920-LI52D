package org.isel.geniuz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.isel.geniuz.lastfm.LastfmWebApiMock

class AlbumsActivity : AppCompatActivity() {

    val lastfm: LastfmWebApiMock by lazy { LastfmWebApiMock() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
        /**
         * Setup recyclerArtists with ArtistsAdapter
         */
        val mbid = intent.getStringExtra(ARTIST_MBID)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerAlbums)
        recyclerView.adapter = AlbumsAdapter(lastfm.getAlbums(mbid, 1))
        recyclerView.layoutManager = LinearLayoutManager(this)
        /**
         * Setup artis name on top of the screen
         */
        val name= intent.getStringExtra(ARTIST_NAME)
        findViewById<TextView>(R.id.txtAlbumsArtistName).text = name
    }
}
