package org.isel.geniuz

import android.app.Application
import androidx.room.Room
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import org.geniuz.lastfm.LastfmWebApi
import org.isel.geniuz.model.ArtistRepository
import org.isel.geniuz.model.GeniuzDb
import java.util.concurrent.TimeUnit

class GeniuzApp : Application() {

    companion object {
        val artistRepo: ArtistRepository by lazy { ArtistRepository() }
        lateinit var lastfm: LastfmWebApi
        lateinit var db: GeniuzDb
    }

    override fun onCreate() {
        super.onCreate()
        lastfm = LastfmWebApi(applicationContext)
        db = Room
                .databaseBuilder(applicationContext,GeniuzDb::class.java, "geniuz-db" )
                .build()
        scheduleBackgroundWork()
    }

    private fun scheduleBackgroundWork() {
        // val request = PeriodicWorkRequestBuilder<WorkerTopChart>(15, TimeUnit.MINUTES).build()
        val request = OneTimeWorkRequestBuilder<WorkerTopChart>()
            .setInitialDelay(10, TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance(applicationContext).enqueue(request)
    }
}