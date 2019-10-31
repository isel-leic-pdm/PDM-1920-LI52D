package org.isel.geniuz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AlbumsViewModelProviderFactory() : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            AlbumsViewModel::class.java -> AlbumsViewModel(GeniuzApp.lastfm) as T
            else -> throw IllegalArgumentException("There is no ViewModel for class $modelClass")
        }
    }
}