package org.isel.pdm

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Line : Parcelable {

    private val points : MutableList<XyPair> = mutableListOf()

    constructor(x: Float, y: Float) {
        add(x, y)
    }

    constructor(parcel: Parcel){
        // parcel.readList(points as List<XyPair>, XyPair::class.java.classLoader )

        val size = parcel.readInt()
        for(i in 0 until size) {
            val pt = parcel.readParcelable<XyPair>(Line::class.java.classLoader)
            points.add(pt!!)
        }
    }
    /**
     * PARCELABLE
     */
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        // val lst: List<XyPair> = points
        // parcel.writeList(lst)
        parcel.writeInt(points.size)
        points.forEach { parcel.writeParcelable(it, flags) }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Line> {
        override fun createFromParcel(parcel: Parcel): Line {
            return Line(parcel)
        }

        override fun newArray(size: Int): Array<Line?> {
            return arrayOfNulls(size)
        }
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Line

        if (points != other.points) return false

        return true
    }

    override fun hashCode(): Int {
        return points.hashCode()
    }


}
