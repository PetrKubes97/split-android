package cz.petrkubes.split.ui.main.repositories

import android.arch.lifecycle.LiveData
import cz.petrkubes.split.ui.main.core.data.User
import cz.petrkubes.split.ui.main.core.database.Database
import javax.inject.Inject

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 13/08/2017
 */
class UserRepository @Inject constructor(val database: Database){

    fun saveFriend(user: User) {
        val task: Runnable = Runnable {
            database.userDao().insert(user)
        }
        val thread = Thread(task)
        thread.start()
    }

    fun getFriends(): LiveData<User> {
        return database.userDao().getAll()
    }
}
