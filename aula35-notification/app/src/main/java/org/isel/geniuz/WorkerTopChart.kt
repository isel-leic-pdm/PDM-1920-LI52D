package org.isel.geniuz

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import org.isel.geniuz.lastfm.dto.TopChartDto
import org.isel.geniuz.model.TopChartTrack
import java.lang.Exception
import java.util.concurrent.CompletableFuture

const val NOTIFICATION_ID = 100012

class WorkerTopChart(context: Context, workerParams: WorkerParameters) : Worker(context,
    workerParams
){
    override fun doWork(): Result {
        val cf = CompletableFuture<TopChartDto>()
        Log.v(TAG, "Get TopChart in background...")
        GeniuzApp.lastfm.getTopChart({
            cf.complete(it)
        }, {
            cf.completeExceptionally(it)
        })
        return try {
            val dto: TopChartDto = cf.get()
            Log.v(TAG, "Get TopChart COMPLETED")
            val arr = dtoToModel(dto)
            GeniuzApp.db.topchartDao().insertAll(*arr)
            /**
             * Check if there is any modification on Top Chart and
             * notify the Channel in that case TO DO !!!!
             */
            notifyTopChartChannel()
            Result.success()
        } catch (e: Exception) {
            Log.v(TAG, "Get TopChart FAILED")
            Result.failure()
            throw e
        }
    }

    private fun notifyTopChartChannel() {
        val intent = Intent(applicationContext, ChartActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)

        val notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Top Chart")
            .setContentText("Update on Top Chart from Last.fm!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        /**
         * 5. Send the notification
         */
        NotificationManagerCompat
            .from(applicationContext)
            .notify(NOTIFICATION_ID, notification)
    }

    private fun dtoToModel(dto: TopChartDto): Array<TopChartTrack> {
        return dto
            .tracks
            .track
            .map { TopChartTrack( it.name, it.url, it.duration, it.playcount, it.listeners)}
            .toTypedArray()
    }

}