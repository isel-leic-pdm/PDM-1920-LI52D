package org.isel.geniuz

import android.os.Looper.getMainLooper
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import org.awaitility.kotlin.await
import org.isel.geniuz.model.Artist
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.LooperMode
import org.robolectric.annotation.LooperMode.Mode.PAUSED
import java.util.concurrent.CompletableFuture

@RunWith(RobolectricTestRunner::class)
@LooperMode(PAUSED)
class ArtistRepositoryTest {
    @Test
    fun testFindByMuse() {
        /**
         * AAA (Arrange Act Assert)
         * Arrange
         */
        val ctr = Robolectric.buildActivity(MainActivity::class.java).setup()
        val all: LiveData<List<Artist>> = GeniuzApp.artistRepo.findByName("Muse")
        /**
         * Assert
         */
        val finish = CompletableFuture<Unit>()
        all.observe(ctr.get(), Observer {
            println("LiveData notified!!!")
            if(it != null && it.size != 0) {
                assertEquals(it[0].name, "Muse")
                assertEquals(it[0].mbid, "fd857293-5ab8-40de-b29e-55a69d4e4d0f")
                finish.complete(null)
            }
        })
        /**
         * Wait for background tasks completion and Flush foreground tasks
         */
        await.pollInSameThread().until {
            // Robolectric.flushForegroundThreadScheduler()
            shadowOf(getMainLooper()).idle()
            finish.isDone
        }
    }
}
