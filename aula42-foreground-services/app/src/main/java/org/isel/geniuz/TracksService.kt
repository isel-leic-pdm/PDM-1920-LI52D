package org.isel.geniuz;

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

const val ACTION_PLAY: String = "com.example.action.PLAY"
const val CHANNEL_TRACK_ID = "CHANNEL_TRACK_TO_PLAY"
const val ONGOING_NOTIFICATION_ID = 100014

class TracksService : Service(), MediaPlayer.OnPreparedListener {

    private val mMediaPlayer: MediaPlayer by lazy { MediaPlayer() }

    init {
        Log.v(TAG, "TrackService instantiated!!!")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val track = intent.getParcelableExtra<Track>(TRACK_OBJECT)
        /**
         * Setup Foreground service
         */
        GeniuzApp.createNotificationChannel(
            CHANNEL_TRACK_ID,
            "channelTrackToPlay",
            "Channel to play tracks from TracksActivity",
            applicationContext
        )
        putInForeground(track)
        /**
         * Setup Music Player
         */
        playTrack(intent, track)
        return super.onStartCommand(intent, flags, startId)
    }

    fun playTrack(intent: Intent, track: Track?) {
        val action: String = intent.action!!
        when(action) {
            ACTION_PLAY -> {
                mMediaPlayer.apply {
                    if(isPlaying) { stop(); reset() }
                    setDataSource(applicationContext, track!!.uri)
                    setOnPreparedListener(this@TracksService)
                    prepareAsync() // prepare async to not block main thread
                }

            }
        }
    }

    /** Called when MediaPlayer is ready */
    override fun onPrepared(mediaPlayer: MediaPlayer) {
        mediaPlayer.start()
    }

    override fun onBind(intent: Intent): IBinder? {
        // We don't provide binding, so return null
        return null
    }

    override fun onDestroy() {
        Log.v(TAG, "TrackService destroyed!!!")
        super.onDestroy()
        mMediaPlayer?.release()
    }
    private fun putInForeground(track: Track) {
        val pendingIntent: PendingIntent =
            Intent(this, TracksActivity::class.java).let { notificationIntent ->
                PendingIntent.getActivity(this, 0, notificationIntent, 0)
            }
        val notification: Notification = Notification
            .Builder(this, CHANNEL_TRACK_ID)
            .setContentTitle("Playing ${track.title}")
            .setContentText("Playing track ${track.title}. Click here to return to Tracks.")
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(ONGOING_NOTIFICATION_ID, notification)
    }
}
