package org.isel.pdm

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class SketcherView(ctx:Context, attrs:AttributeSet? = null): View(ctx, attrs) {

    /*
    constructor(ctx:Context) :super(ctx){}
    constructor(ctx:Context, attrs:AttributeSet): super(ctx, attrs) { }
    */

    val lines : MutableList<Line> = mutableListOf()
    var curr : Line? = null

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        lines.forEach { it.draw(canvas)}
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                curr = Line(event.x, event.y)
                lines.add(curr!!)
            }
            MotionEvent.ACTION_MOVE -> {
                curr?.add(event.x, event.y)
            }
        }
        return true
    }
}