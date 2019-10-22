package org.isel.geniuz.lastfm.dto

import com.google.gson.annotations.SerializedName

class ImageDto(
    @field:SerializedName("#text")
    val uri: String,
    val size: String
) {
    override fun toString(): String {
        return "ImageDto(\"$uri\", \"$size\")"
    }
}
