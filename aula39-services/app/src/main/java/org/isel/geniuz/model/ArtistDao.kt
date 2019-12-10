package org.isel.geniuz.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArtistDao {
    @Query("SELECT * FROM artists")
    fun getAll(): LiveData<List<Artist>>

    @Query("SELECT * FROM artists WHERE name LIKE :name")
    fun findByName(name: String): LiveData<List<Artist>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: Artist)

    @Delete
    fun delete(user: Artist)
}