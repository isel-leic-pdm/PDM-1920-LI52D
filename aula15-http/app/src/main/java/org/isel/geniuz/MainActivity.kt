package org.isel.geniuz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.isel.geniuz.lastfm.LastfmWebApiMock
import org.isel.geniuz.lastfm.dto.ArtistDto

const val TAG : String = "GENIUZ_APP"

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val lastfm : LastfmWebApiMock by lazy {
        LastfmWebApiMock()
    }
    val adapter : ArtistsAdapter by lazy {
        ArtistsAdapter(emptyArray())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * Setup recyclerArtists with ArtistsAdapter
         */
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerArtists)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        /**
         * Setup search button
         */
        findViewById<Button>(R.id.buttonSearch).setOnClickListener(this)
    }
    override fun onClick(v: View) {
        /**
         * Fetch data
         */
        Log.v(TAG, "**** FETCHING from Last.fm...")
        val name = txtSearchArtistName.text.toString()
        val artists = lastfm.searchArtist(name, 1)
        /**
         * Update UI
         */
        adapter.artists = artists
        adapter.notifyDataSetChanged()
        txtTotalArtists.text = artists.size.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        isChangingConfigurations()
    }
}
