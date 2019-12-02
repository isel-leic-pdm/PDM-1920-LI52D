package org.isel.geniuz

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import org.geniuz.lastfm.LastfmWebApi
import org.isel.geniuz.model.ArtistRepository
import org.isel.geniuz.model.GeniuzDb
import java.util.concurrent.TimeUnit

const val CHANNEL_ID = "GENIUZ_CHANNEL_TOPCHART"

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
    private fun createNotificationChannel() {
        // 1. Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        val name = getString(R.string.channel_name)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // 2. Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}