package org.isel.geniuz

import androidx.lifecycle.ViewModelProviders
import org.awaitility.kotlin.await
import org.isel.geniuz.lastfm.dto.ArtistDto
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.Robolectric.flushBackgroundThreadScheduler
import org.robolectric.Robolectric.flushForegroundThreadScheduler
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.LooperMode
import java.util.concurrent.CompletableFuture

@RunWith(RobolectricTestRunner::class)
class ArtistsViewModelTest {
    @Test
    fun testSearch() {
        /**
         * Arrange
         */
        val ctrl = Robolectric.buildActivity<MainActivity>(MainActivity::class.java)
            .setup()
        val factory = ArtistsViewModelProviderFactory(null)
        val model = ViewModelProviders.of(ctrl.get(), factory)[ArtistsViewModel::class.java]
        /**
         * Act
         */
        val cf = CompletableFuture<Array<ArtistDto>>()
        model.observe(ctrl.get()) { if(it.isNotEmpty()) cf.complete(it) }
        model.searchArtist("muse")
        await.pollInSameThread().until {
            flushBackgroundThreadScheduler()
            flushForegroundThreadScheduler()
            cf.isDone
        }
        /**
         * Assert
         */
        val dto = cf.join()[2]
        assertEquals("Danger Mouse", dto.name)
        assertEquals("4b356f05-bcc2-4544-925b-fd9a1bf708be", dto.mbid)
    }
}
