package org.isel.geniuz.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Artist::class, TopChartTrack::class), version = 3)
abstract class GeniuzDb: RoomDatabase() {
    abstract fun artistDao(): ArtistDao
    abstract fun topchartDao(): TopChartDao
}