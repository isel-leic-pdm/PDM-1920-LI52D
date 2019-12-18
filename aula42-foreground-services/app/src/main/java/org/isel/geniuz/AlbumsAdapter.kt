package org.isel.geniuz

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.isel.geniuz.lastfm.dto.AlbumDto

class AlbumsAdapter(private val model: AlbumsViewModel)
    : RecyclerView.Adapter<AlbumViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.album_view, parent, false) as LinearLayout
        return AlbumViewHolder(view)
    }

    override fun getItemCount(): Int = model.albums.value?.size ?: 0

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bindTo(model.albums.value!![position])
    }
}

class AlbumViewHolder(private val view: LinearLayout) : RecyclerView.ViewHolder(view) {
    private val txtAlbumName: TextView = view.findViewById<TextView>(R.id.txtAlbumName)
    private val txtPlaycount: TextView = view.findViewById<TextView>(R.id.txtPlaycount)

    fun bindTo(album: AlbumDto) {
        txtAlbumName.text = album.name
        txtPlaycount.text = "Playcount: " + album.playcount
    }
}
