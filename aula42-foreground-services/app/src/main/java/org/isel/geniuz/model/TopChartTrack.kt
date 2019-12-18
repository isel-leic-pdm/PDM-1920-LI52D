package org.isel.geniuz.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topchart")
class TopChartTrack (
    @PrimaryKey val name: String,
    val url: String,
    val duration: Int,
    val playcount: Int,
    val listeners: Int
)