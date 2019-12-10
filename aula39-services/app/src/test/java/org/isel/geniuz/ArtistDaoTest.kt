package org.isel.geniuz

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
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
        val ctr = Robolectric.buildActivity(MainActivity::class.java).setup()
        val all: LiveData<List<Artist>> = GeniuzApp.db.artistDao().getAll()
        val maria = Artist("uugdfkda", "Maria Papoila", "fakeurl", "ole")
        /**
         * Act
         */
        println("Thread ${Thread.currentThread().hashCode()}")
        asyncInsert(maria) // !!!! .get() // Wait for Insert completion
        /**
         * Assert
         */
        val finish = CompletableFuture<Unit>()
        all.observe(ctr.get(), Observer {
            if(it != null && it.size != 0) {
                assertEquals(it[0].name, "Maria Papoila")
                finish.complete(null)
            }
        })
        await.pollInSameThread().until {
            Robolectric.flushForegroundThreadScheduler()
            finish.isDone
        }
    }

    fun asyncInsert(artist: Artist) =
        CompletableFuture.supplyAsync {
            println("Thread ${Thread.currentThread().hashCode()}")
            GeniuzApp.db.artistDao().insertAll(artist)
        }
}
