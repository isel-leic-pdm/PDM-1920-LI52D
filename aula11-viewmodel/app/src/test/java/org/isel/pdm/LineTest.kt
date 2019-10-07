package org.isel.pdm

import android.os.Parcel
import android.text.ParcelableSpan
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class LineTest {
    @Test
    fun testLineParcelable() {
        /**
         * Arrange
         */
        val line = Line(5F, 7F)
        line.add(9F,11F)
        line.add(77F,2F)
        /**
         * Act
         */
        val parcel = Parcel.obtain()
        line.writeToParcel(parcel, 0)
        parcel.setDataPosition(0);
        val actual = Line.CREATOR.createFromParcel(parcel)
        /*
         * Assert
         */
        assertEquals(line, actual)
    }
}
