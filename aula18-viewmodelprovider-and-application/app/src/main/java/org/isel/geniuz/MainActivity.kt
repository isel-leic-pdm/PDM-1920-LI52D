package org.isel.geniuz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.geniuz.lastfm.LastfmWebApi

const val TAG : String = "GENIUZ_APP"

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val lastfm : LastfmWebApi by lazy {
        LastfmWebApi(this)
    }
    val adapter : ArtistsAdapter by lazy {
        ArtistsAdapter(model)
    }
    val model : ArtistsViewModel by lazy {
        ViewModelProviders.of(this)[ArtistsViewModel::class.java]
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
        val name = txtSearchArtistName.text.toString()
        Log.v(TAG, "**** FETCHING Artists $name from Last.fm...")
        lastfm.searchArtist(name, 1, {artists ->
            Log.v(TAG, "**** FETCHING Artists $name COMPLETED !!!!")
            /**
             * Update UI
             */
            model.artists = artists.results.artistMatches.artist
            adapter.notifyDataSetChanged()
            txtTotalArtists.text = artists.results.totalResults.toString()
        }, {err -> throw err})

    }
}
