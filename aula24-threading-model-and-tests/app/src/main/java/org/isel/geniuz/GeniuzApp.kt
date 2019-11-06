package org.isel.geniuz

import android.app.Application
import org.geniuz.lastfm.LastfmWebApi

class GeniuzApp : Application() {

    companion object {
        lateinit var lastfm: LastfmWebApi
    }

    override fun onCreate() {
        super.onCreate()
        lastfm = LastfmWebApi(applicationContext)
    }
}