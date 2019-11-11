package org.isel.geniuz

import android.app.Application
import androidx.room.Room
import org.geniuz.lastfm.LastfmWebApi
import org.isel.geniuz.model.GeniuzDb

class GeniuzApp : Application() {

    companion object {
        lateinit var lastfm: LastfmWebApi
        lateinit var db: GeniuzDb
    }

    override fun onCreate() {
        super.onCreate()
        lastfm = LastfmWebApi(applicationContext)
        db = Room
                .databaseBuilder(applicationContext,GeniuzDb::class.java, "geniuz-db" )
                .build()
    }
}