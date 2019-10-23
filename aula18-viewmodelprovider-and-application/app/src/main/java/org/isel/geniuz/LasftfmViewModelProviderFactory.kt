package org.isel.geniuz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.geniuz.lastfm.LastfmWebApi

class LasftfmViewModelProviderFactory(val app:GeniuzApp) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            AlbumsViewModel::class.java -> AlbumsViewModel(app.lastfm) as T
            ArtistsViewModel::class.java -> ArtistsViewModel(app.lastfm) as T
            else -> throw IllegalArgumentException("There is no ViewModel for class $modelClass")
        }
    }
}