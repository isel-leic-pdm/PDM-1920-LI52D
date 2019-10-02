package org.isel.pdm

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import java.io.Serializable

class Line : Serializable {

    private val points : MutableList<XyPair> = mutableListOf()

    constructor(x: Float, y: Float) {
        add(x, y)
    }

    fun draw(canvas: Canvas) {
        val paint = Paint()
        paint.setColor(Color.BLUE)
        paint.strokeWidth = 20f

        points.reduce { prev, curr ->
            canvas.drawLine(prev.x, prev.y, curr.x, curr.y, paint)
            curr
        }
    }

    fun add(x: Float, y: Float) {
        points.add(XyPair(x, y))
    }


}
