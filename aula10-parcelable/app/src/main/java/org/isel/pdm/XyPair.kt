package org.isel.pdm

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class XyPair (val x: Float, val y: Float) : Serializable, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readFloat(),
        parcel.readFloat()
    ) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeFloat(x)
        dest?.writeFloat(y)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<XyPair> {
        override fun createFromParcel(parcel: Parcel): XyPair {
            return XyPair(parcel)
        }

        override fun newArray(size: Int): Array<XyPair?> {
            return arrayOfNulls(size)
        }
    }

}
