package org.isel.geniuz

import androidx.lifecycle.ViewModel
import org.isel.geniuz.lastfm.dto.ArtistDto

class ArtistsViewModel(var artists : Array<ArtistDto> = emptyArray()) : ViewModel()