package cz.petrkubes.split.ui.main.model.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import cz.petrkubes.split.ui.main.model.data.Friend

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 11/08/2017
 */
@Dao
interface FriendDao {

    @Insert
    fun insert(friend: Friend)

    @Query("SELECT * FROM friend")
    fun getAll(): LiveData<Friend>
}
