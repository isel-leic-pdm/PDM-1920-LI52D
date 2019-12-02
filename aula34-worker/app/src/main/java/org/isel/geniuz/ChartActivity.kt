package org.isel.geniuz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.isel.geniuz.model.TopChartTrack

class ChartActivity : AppCompatActivity() {

    val model: ChartViewModel by lazy {
        ViewModelProviders.of(this)[ChartViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        /**
         * Setup recycler view.
         */
        val chart = findViewById<RecyclerView>(R.id.recyclerChartTracks)
        val adapter = TopChartAdapter(model)
        chart.adapter = adapter
        chart.layoutManager = LinearLayoutManager(this)
        model.topchart.observe(this, Observer {
            adapter.notifyDataSetChanged()
        })
    }
}

class TopChartAdapter(val model: ChartViewModel) : RecyclerView.Adapter<TopChartViewHolder>() {
    override fun onBindViewHolder(holder: TopChartViewHolder, position: Int) {
        holder.bindTo(model.topchart.value!![position])
    }

    override fun getItemCount(): Int = model.topchart.value?.count() ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopChartViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.chart_track_view, parent, false) as LinearLayout
        return TopChartViewHolder(layout)
    }
}

class TopChartViewHolder(private val view: LinearLayout) : RecyclerView.ViewHolder(view) {
    val trackName = view.findViewById<TextView>(R.id.chartTrackName)
    val trackListeners = view.findViewById<TextView>(R.id.charTrackListeners)
    fun bindTo(track: TopChartTrack) {
        trackName.text = track.name
        trackListeners.text = track.listeners.toString()
    }

}
