package org.isel.geniuz.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artists")
class Artist(
    @PrimaryKey val mbid: String,
    var name: String,
    var url: String,
    var imageUri: String
)