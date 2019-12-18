package org.isel.geniuz

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import org.isel.geniuz.model.Artist


const val ARTIST_MBID = "ARTIST_MBID"
const val ARTIST_NAME = "ARTIST_NAME"
const val CHECK_ARTIST_DIALOG = "CHECK_ARTIST_DIALOG"

class ArtistsAdapter(
    val model: ArtistsViewModel,
    val mgr: AppCompatActivity
)
    : RecyclerView.Adapter<ArtistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        // 1. Obter o TextView i.e. cell_view
        // 2. Inflate parent com o cell_view
        // 3. Instanciar ViewHolder -> passando-lhe o TextView
        val artistsView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_view, parent, false) as LinearLayout
        return ArtistViewHolder(artistsView, mgr)
    }

    override fun getItemCount(): Int = model.artists.size

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bindTo(model.artists[position])
    }
}

class ArtistViewHolder(
    private val view: LinearLayout,
    private val mgr: AppCompatActivity
) : RecyclerView.ViewHolder(view) {
    private lateinit var artist: Artist
    private val txtArtistName: TextView = view.findViewById<TextView>(R.id.txtMain)
    private val txtArtistUrl: TextView = view.findViewById<TextView>(R.id.txtDetails)

    init {
        view.setOnClickListener {
            CheckArtist(artist, view).show(mgr.supportFragmentManager, CHECK_ARTIST_DIALOG)
        }
    }

    fun bindTo(artist: Artist) {
        this.artist = artist
        txtArtistName.text = artist.name
        txtArtistUrl.text = artist.url
    }
}

class CheckArtist(
    private val artist: Artist,
    private val view: LinearLayout
) : DialogFragment()
{
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Get albums or Check tracks on device?")
                .setPositiveButton("Albums") { _, _ ->
                    val intent = Intent(view.context, AlbumsActivity::class.java)
                    intent.putExtra(ARTIST_MBID, artist.mbid)
                    intent.putExtra(ARTIST_NAME, artist.name)
                    view.context.startActivity(intent)
                }
                .setNegativeButton("Tracks") { _, _ ->
                    val intent = Intent(view.context, TracksActivity::class.java)
                    intent.putExtra(ARTIST_NAME, artist.name)
                    view.context.startActivity(intent)
                }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}