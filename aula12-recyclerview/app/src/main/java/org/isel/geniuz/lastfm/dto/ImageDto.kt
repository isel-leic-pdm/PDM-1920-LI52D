package org.geniuz.lastfm.dto

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @field:SerializedName("#text")
    val uri: String, val size: String
)
