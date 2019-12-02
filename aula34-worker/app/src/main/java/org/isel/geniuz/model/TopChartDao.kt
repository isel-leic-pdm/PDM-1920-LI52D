package org.isel.geniuz.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TopChartDao {
    @Query("SELECT * FROM topchart")
    fun getAll(): LiveData<List<TopChartTrack>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: TopChartTrack)

}