package org.isel.geniuz

import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.*
import org.isel.geniuz.model.Artist

class ArtistsViewModel() : ViewModel(), Parcelable {

    private val liveData : MutableLiveData<Array<Artist>> = MutableLiveData(emptyArray())

    val artists : Array<Artist>
        get() = liveData.value!!

    private var current : String? = null

    constructor(parcel: Parcel) : this() {
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
        val res: LiveData<List<Artist>> = GeniuzApp.artistRepo.findByName(name)
        MediatorLiveData<List<Artist>>().addSource(res, Observer {
            println("**** FETCHING Artists $name COMPLETED !!!!")
            liveData.value = it.toTypedArray()
        })
    }

    fun observe(owner: LifecycleOwner, observer: (Array<Artist>) -> Unit) {
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