package org.isel.geniuz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.isel.geniuz.lastfm.LastfmWebApiMock

class MainActivity : AppCompatActivity() {

    val lastfm : LastfmWebApiMock by lazy { LastfmWebApiMock() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * Setup recyclerArtists with ArtistsAdapter
         */
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerArtists)
        recyclerView.adapter = ArtistsAdapter(lastfm.searchArtist("muse", 1))
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
