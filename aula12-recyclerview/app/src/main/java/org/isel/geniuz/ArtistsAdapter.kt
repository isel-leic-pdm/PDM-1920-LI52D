package org.isel.geniuz

import android.view.LayoutInflater
import android.view.ViewGroup
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
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.artist_view, parent, false) as TextView
        return ArtistViewHolder(textView)
    }

    override fun getItemCount(): Int = artists.size

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.txtView.text = artists[position].name
    }
}

class ArtistViewHolder(val txtView: TextView) : RecyclerView.ViewHolder(txtView) {

}
