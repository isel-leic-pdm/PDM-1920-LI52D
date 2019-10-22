package org.isel.geniuz

import androidx.lifecycle.ViewModel
import org.isel.geniuz.lastfm.dto.AlbumDto

class AlbumsViewModel(var albums : Array<AlbumDto> = emptyArray()) : ViewModel()