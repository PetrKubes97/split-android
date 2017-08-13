package cz.petrkubes.split.ui.main.ui.activities.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import cz.petrkubes.split.ui.main.model.data.User
import cz.petrkubes.split.ui.main.model.database.Database

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 06/08/2017
 */
class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var userLD: MutableLiveData<User>

    val database: Database = Room.databaseBuilder(application.applicationContext, Database::class.java, "split-db").build()

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
