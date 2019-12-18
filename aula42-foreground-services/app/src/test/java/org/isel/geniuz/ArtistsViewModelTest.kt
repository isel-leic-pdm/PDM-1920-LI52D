package org.isel.geniuz

import android.os.Looper
import androidx.lifecycle.ViewModelProviders
import org.awaitility.kotlin.await
import org.isel.geniuz.model.Artist
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.LooperMode
import java.util.concurrent.CompletableFuture

@RunWith(RobolectricTestRunner::class)
@LooperMode(LooperMode.Mode.PAUSED)
class ArtistsViewModelTest {
    @Test
    fun testSearchArtists() {
        /**
         * AAA (Arrange Act Assert)
         * Arrange
         */
        val ctr = Robolectric.buildActivity(MainActivity::class.java).setup()
        val factory = ArtistsViewModelProviderFactory(null)
        val model = ViewModelProviders.of(ctr.get(), factory)[ArtistsViewModel::class.java]
        val cf = CompletableFuture<Array<Artist>>()
        model.observe(ctr.get()) {
            println("LiveData notified!!!")
            if(it.isNotEmpty())
                cf.complete(it)
        }
        /**
         * Act
         */
        model.searchArtist("mouse")
        await.pollInSameThread().until {
            // Robolectric.flushForegroundThreadScheduler()
            Shadows.shadowOf(Looper.getMainLooper()).idle()
            cf.isDone
        }
        /**
         * Assert
         */
        assertEquals("Danger Mouse and Sparklehorse", cf.get()[2].name)
        assertEquals("4b356f05-bcc2-4544-925b-fd9a1bf708be", cf.get()[2].mbid)
    }
}
