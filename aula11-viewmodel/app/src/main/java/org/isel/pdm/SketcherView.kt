package org.isel.pdm

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

class SketcherView(ctx:Context, attrs:AttributeSet? = null): View(ctx, attrs) {

    val modelView by lazy {
        val main = context as AppCompatActivity
        ViewModelProviders.of(main)[SketcherViewModel::class.java]
    }

    private var curr : Line? = null

    companion object val PAINT = Paint()
    init {
        PAINT.setColor(Color.BLUE)
        PAINT.strokeWidth = 20f
    }

    override fun onDraw(canvas: Canvas) = modelView.draw(canvas, PAINT)

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                curr = Line(event.x, event.y)
                modelView.add(curr!!)
            }
            MotionEvent.ACTION_MOVE -> {
                curr?.add(event.x, event.y)
            }
        }
        invalidate()
        return true
    }
}