package org.isel.geniuz

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import org.awaitility.kotlin.await
import org.isel.geniuz.lastfm.dto.ArtistDto
import org.isel.geniuz.model.Artist
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import java.util.concurrent.CompletableFuture

@RunWith(RobolectricTestRunner::class)
class ArtistDaoTest {
    @Test
    fun testInsertAndRead() {
        /**
         * AAA (Arrange Act Assert)
         * Arrange
         */
        val maria = Artist("uugdfkda", "Maria Papoila", "fakeurl", "ole")
        println("Thread ${Thread.currentThread().hashCode()}")
        asyncInsert(maria).get() // Wait for Insert completion
        val all: LiveData<List<Artist>> = GeniuzApp.db.artistDao().getAll()
        /**
         * Assert
         */
        assertNotNull(all.value)
        assertEquals(all.value!![0].name, "Maria Papoila")
    }
    fun asyncInsert(artist: Artist): AsyncTask<Artist, Int, Unit> {
        val task = object : AsyncTask<Artist, Int, Unit>(){
            override fun doInBackground(vararg params: Artist?) {
                println("Thread ${Thread.currentThread().hashCode()}")
                GeniuzApp.db.artistDao().insertAll(artist)
            }
        }
        return task.execute(artist)
    }
}
