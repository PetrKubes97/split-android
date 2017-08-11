package cz.petrkubes.split.ui.main.ui.activities.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import cz.petrkubes.split.ui.main.model.data.Friend
import cz.petrkubes.split.ui.main.model.database.Database

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 06/08/2017
 */
class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var friendLD: MutableLiveData<Friend>

    val database: Database = Room.databaseBuilder(application.applicationContext, Database::class.java, "split-db").build()

    fun saveFriend(friend: Friend) {

        val task: Runnable = Runnable {
            database.friendDao().insert(friend)
        }

        val thread = Thread(task)
        thread.start()
    }

    fun getFriends(): LiveData<Friend> {
        return database.friendDao().getAll()
    }

}
