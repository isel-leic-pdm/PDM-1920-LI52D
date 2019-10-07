package org.isel.pdm

import android.graphics.Canvas
import android.graphics.Paint
import androidx.lifecycle.ViewModel

class SketcherViewModel : ViewModel() {

    private val lines = ArrayList<Line>()

    fun draw(canvas: Canvas, paint: Paint) {
        lines.forEach {
            it.reduce { prev, curr ->
                canvas.drawLine(prev.x, prev.y, curr.x, curr.y, paint)
                curr
            }
        }
    }

    fun add(curr: Line) {
        lines.add(curr)
    }
}