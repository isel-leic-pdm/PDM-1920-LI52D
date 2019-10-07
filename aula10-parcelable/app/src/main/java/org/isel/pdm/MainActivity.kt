package org.isel.pdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


const val SKETCHER_STATE = "SKETCHER_STATE"

class MainActivity : AppCompatActivity() {

    val sketcher: SketcherView by lazy {
        findViewById<SketcherView>(R.id.sketcherView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(SKETCHER_STATE, sketcher.lines)
    }

    override fun onRestoreInstanceState(src: Bundle) {
        super.onRestoreInstanceState(src)
        sketcher.lines = src.getParcelableArrayList<Line>(SKETCHER_STATE) as ArrayList<Line>
    }
}
