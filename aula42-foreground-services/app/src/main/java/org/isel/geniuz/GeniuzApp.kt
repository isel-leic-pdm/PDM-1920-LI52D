package org.isel.geniuz

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.work.OneTimeWorkRequestBuilder
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

        fun createNotificationChannel(id: String, name: String, desc: String, ctx: Context) {
            // 1. Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val importance = NotificationManager.IMPORTANCE_DEFAULT

                val channel = NotificationChannel(id, name, importance).apply {
                    description = desc
                }
                // 2. Register the channel with the system
                val notificationManager: NotificationManager =
                    ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        lastfm = LastfmWebApi(applicationContext)
        db = Room
                .databaseBuilder(applicationContext,GeniuzDb::class.java, "geniuz-db" )
                .build()
        createNotificationChannel(
            CHANNEL_ID,
            getString(R.string.channel_name),
            getString(R.string.channel_description),
            applicationContext
        )
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