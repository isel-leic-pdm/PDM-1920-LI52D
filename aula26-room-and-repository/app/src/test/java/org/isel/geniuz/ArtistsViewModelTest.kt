package org.isel.geniuz

import androidx.lifecycle.ViewModelProviders
import org.awaitility.kotlin.await
import org.isel.geniuz.model.Artist
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import java.util.concurrent.CompletableFuture

@RunWith(RobolectricTestRunner::class)
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
            if(it.size > 0)
                cf.complete(it)
        }
        /**
         * Act
         */
        model.searchArtist("muse")
        await.pollInSameThread().until {
            // Robolectric.flushBackgroundThreadScheduler()
            Robolectric.flushForegroundThreadScheduler()
            cf.isDone
        }
        /**
         * Assert
         */
        assertEquals("Danger Mouse", cf.get()[2].name)
    }
}
