package org.isel.geniuz

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.geniuz.lastfm.dto.ArtistDto

class ArtistsAdapter(private val artists: Array<ArtistDto>)
    : RecyclerView.Adapter<ArtistViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        // 1. Obter o TextView i.e. artist_view
        // 2. Inflate parent com o artist_view
        // 3. Instanciar ViewHolder -> passando-lhe o TextView
        val artistsView = LayoutInflater.from(parent.context)
            .inflate(R.layout.artist_view, parent, false) as LinearLayout
        return ArtistViewHolder(artistsView)
    }

    override fun getItemCount(): Int = artists.size

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bindTo(artists[position])
    }
}

class ArtistViewHolder(private val artistsView: LinearLayout) : RecyclerView.ViewHolder(artistsView) {
    private val txtArtistName: TextView = artistsView.findViewById<TextView>(R.id.txtArtistName)
    private val txtArtistUrl: TextView = artistsView.findViewById<TextView>(R.id.txtArtistUrl)

    fun bindTo(artist: ArtistDto) {
        txtArtistName.text = artist.name
        txtArtistUrl.text = artist.url
    }
}
