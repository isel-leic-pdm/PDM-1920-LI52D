package org.isel.pdm

import android.content.Context
import android.graphics.Canvas
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.PointerIcon
import android.view.View

class SketcherView(ctx:Context, attrs:AttributeSet? = null): View(ctx, attrs) {

    /*
    constructor(ctx:Context) :super(ctx){}
    constructor(ctx:Context, attrs:AttributeSet): super(ctx, attrs) { }
    */

    private val lines : MutableList<Line> = mutableListOf()
    private var curr : Line? = null

    var state : Array<Line>
        get() = lines.toTypedArray()
        set(value) { lines.addAll(value) }

    override fun onDraw(canvas: Canvas) = lines.forEach { it.draw(canvas)}


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
        invalidate()
        return true
    }

    /*
    override fun onSaveInstanceState(): Parcelable? {
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        super.onRestoreInstanceState(state)
    }
    */
}