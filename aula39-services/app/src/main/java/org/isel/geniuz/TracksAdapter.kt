package org.isel.geniuz

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

const val TRACK_OBJECT = "TRACK_OBJECT"

class TracksAdapter(
    val tracks: Array<Track>,
    val act: TracksActivity
)
    : RecyclerView.Adapter<TrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val artistsView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_view, parent, false) as LinearLayout
        return TrackViewHolder(artistsView, act)
    }

    override fun getItemCount(): Int = tracks.size

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bindTo(tracks[position])
    }
}

class TrackViewHolder(
    view: LinearLayout,
    private val act: TracksActivity
) : RecyclerView.ViewHolder(view) {
    private lateinit var track: Track
    private val txtTitle: TextView = view.findViewById<TextView>(R.id.txtMain)
    private val txtArtist: TextView = view.findViewById<TextView>(R.id.txtDetails)

    init {
        view.setOnClickListener { act.play(track) }
    }

    fun bindTo(track: Track) {
        this.track = track
        txtTitle.text = track.title
        txtArtist.text = track.artist
    }
}
