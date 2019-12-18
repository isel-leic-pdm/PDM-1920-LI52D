package org.isel.geniuz

import android.Manifest.permission.FOREGROUND_SERVICE
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.ContentUris
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_tracks.*

const val MY_PERMISSIONS_READ_EXTERNAL_STORAGE = 901

class TracksActivity : AppCompatActivity() {

    private lateinit var track: Track
    private val artist by lazy { intent.getStringExtra(ARTIST_NAME) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracks)
        this.lifecycle.addObserver(LifecycleLogger(componentName.className))
        /**
         * Setup recyclerTracks with TracksAdapter
         */
        recyclerTracks.layoutManager = LinearLayoutManager(this)
        recyclerTracks.adapter = if(checkForPermissions(READ_EXTERNAL_STORAGE))
            TracksAdapter(getTracks(), this)
            else {
                requestPermissions(this,
                    arrayOf(READ_EXTERNAL_STORAGE),
                    MY_PERMISSIONS_READ_EXTERNAL_STORAGE)
                TracksAdapter(emptyArray(), this)
            }
    }

    fun play(track: Track) {
        this.track = track
        playNow()
    }

    fun playNow() {
        MediaPlayer().apply {
            setDataSource(this@TracksActivity, track.uri)
            prepare() // !!!! might take long !!!! (for buffering, etc)
            start()
        }
    }

    private fun checkForPermissions(perm: String): Boolean =
        checkSelfPermission(this, perm) == PERMISSION_GRANTED

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray)
    {
        when (requestCode) {
            MY_PERMISSIONS_READ_EXTERNAL_STORAGE ->
                checkAndDo(grantResults, "No permission to get local tracks!!!!" ) {
                    recyclerTracks.swapAdapter(TracksAdapter(getTracks(), this),true)
                }
            else -> {
                // Ignore all other requests.
            }
        }
    }

    private fun checkAndDo(grantResults: IntArray, msg: String, action: () -> Unit) {
        // If request is cancelled, the result arrays are empty.
        if ((grantResults.isNotEmpty() && grantResults[0] == PERMISSION_GRANTED)) {
            action()
        } else {
            Toast
                .makeText(this, msg, Toast.LENGTH_SHORT)
                .show()
        }
        return
    }

    fun getTracks(): Array<Track> {
        val cr = applicationContext.contentResolver
        val projection = arrayOf(
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media._ID
        )
        val selection = MediaStore.Audio.Media.TITLE + " like ?" // MediaStore.Audio.Media.IS_MUSIC + " != 0"
        val selectionArgs = arrayOf("%${artist}%")
        val sortOrder = MediaStore.Audio.Media.TITLE + " ASC"
        val col = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val res = mutableListOf<Track>()
        cr
            .query(col, projection, selection, selectionArgs, sortOrder)
            ?.use { cursor ->
                while (cursor.moveToNext()) {
                    val title = cursor.getString(0)
                    val artist = cursor.getString(1)
                    val id = cursor.getLong(2)
                    val contentUri: Uri = ContentUris.withAppendedId(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        id
                    )
                    val trk = Track(title, artist, contentUri)
                    res.add(trk)
                    Log.v(TAG, trk.toString())
                }
            }
        if(res.isEmpty())
            Toast
                .makeText(this, "No tracks on device for ${artist}!!!!", Toast.LENGTH_SHORT)
                .show()
        return res.toTypedArray()
    }
}
