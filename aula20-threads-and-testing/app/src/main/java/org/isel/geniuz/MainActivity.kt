package org.isel.geniuz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.geniuz.lastfm.LastfmWebApi

const val TAG : String = "GENIUZ_APP"

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val adapter : ArtistsAdapter by lazy {
        ArtistsAdapter(model)
    }
    val model : ArtistsViewModel by lazy {
        val app = application as GeniuzApp
        val factory = LasftfmViewModelProviderFactory(app)
        ViewModelProviders.of(this, factory)[ArtistsViewModel::class.java]
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
        /**
         * Setup observer on LiveData
         */
        model.observe(this) {
            adapter.notifyDataSetChanged()
            txtTotalArtists.text = it?.size.toString()
        }
    }
    override fun onClick(v: View) {
        val name = txtSearchArtistName.text.toString()
        model.searchArtist(name)
    }
}
