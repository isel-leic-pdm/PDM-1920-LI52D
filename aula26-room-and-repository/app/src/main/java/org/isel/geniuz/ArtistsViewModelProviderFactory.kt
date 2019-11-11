package org.isel.geniuz

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ArtistsViewModelProviderFactory(val savedInstanceState: Bundle?) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var model: ArtistsViewModel? = null
        if(savedInstanceState != null) {
            model = savedInstanceState.getParcelable<ArtistsViewModel>(ARTISTS_VIEW_STATE)
        }
        return if(model != null) {
            Log.v(TAG, "**** RESTORED ArtistsViewModel from Bundle!!!")
            model as T
        }
        else when (modelClass) {
            ArtistsViewModel::class.java -> {
                Log.v(TAG, "**** CREATED ArtistsViewModel from the scratch!!!")
                ArtistsViewModel() as T
            }
            else -> throw IllegalArgumentException("There is no ViewModel for class $modelClass")
        }
    }
}