package org.isel.geniuz

import android.app.Application
import org.geniuz.lastfm.LastfmWebApi

class GeniuzApp : Application() {
    val lastfm by lazy {
        LastfmWebApi(applicationContext)
    }
}