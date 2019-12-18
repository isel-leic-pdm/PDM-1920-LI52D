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

const val TAG : String = "GENIUZ_APP"
const val ARTISTS_VIEW_STATE: String = "ARTISTS_VIEW_STATE"

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val adapter : ArtistsAdapter by lazy {
        ArtistsAdapter(model, this)
    }
    lateinit var model : ArtistsViewModel

    fun initArtistsViewModel(savedInstanceState: Bundle?) : ArtistsViewModel{
        val factory = ArtistsViewModelProviderFactory(savedInstanceState)
        return ViewModelProviders.of(this, factory)[ArtistsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = initArtistsViewModel(savedInstanceState)
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if(isChangingConfigurations)
            return // The ViewModel reamains and we do not need to save in Bundle
        Log.v(TAG, "**** SAVING ArtistsViewModel to Bundle!!!")
        outState.putParcelable(ARTISTS_VIEW_STATE, model)
    }
}
