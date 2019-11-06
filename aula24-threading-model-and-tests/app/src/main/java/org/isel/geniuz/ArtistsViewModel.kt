package org.isel.geniuz

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import org.geniuz.lastfm.LastfmWebApi
import org.isel.geniuz.lastfm.dto.ArtistDto

class ArtistsViewModel(private val lastfm: LastfmWebApi) : ViewModel(), Parcelable {

    private val liveData : MutableLiveData<Array<ArtistDto>> = MutableLiveData(emptyArray())

    val artists : Array<ArtistDto>
        get() = liveData.value!!

    private var current : String? = null

    constructor(parcel: Parcel) : this(GeniuzApp.lastfm) {
        searchArtist(parcel.readString()!!)
    }
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(current)
    }
    override fun describeContents(): Int = 0

    fun searchArtist(name: String)
    {
        current = name
        println("**** FETCHING Artists $name from Last.fm...")
        lastfm.searchArtist(name, 1, {artists ->
            println("**** FETCHING Artists $name COMPLETED !!!!")
            this.liveData.value = artists.results.artistMatches.artist
        }, { throw it })
    }

    fun observe(owner: LifecycleOwner, observer: (Array<ArtistDto>) -> Unit) {
        liveData.observe(owner, Observer {
            observer(it)
        })
    }

    companion object CREATOR : Parcelable.Creator<ArtistsViewModel> {
        override fun createFromParcel(parcel: Parcel): ArtistsViewModel {
            return ArtistsViewModel(parcel)
        }

        override fun newArray(size: Int): Array<ArtistsViewModel?> {
            return arrayOfNulls(size)
        }
    }
}