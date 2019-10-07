package org.isel.pdm

class Line : Sequence<XyPair> {

    private val points : MutableList<XyPair> = mutableListOf()

    constructor(x: Float, y: Float) {
        add(x, y)
    }

    override fun iterator(): Iterator<XyPair> = points.iterator()

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
