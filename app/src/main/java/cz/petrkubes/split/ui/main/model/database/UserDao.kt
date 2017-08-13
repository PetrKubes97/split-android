package cz.petrkubes.split.ui.main.model.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import cz.petrkubes.split.ui.main.model.data.User

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 11/08/2017
 */
@Dao
interface UserDao {

    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM user")
    fun getAll(): LiveData<User>
}