package cz.petrkubes.split.ui.main.core.database

import android.app.Application
import android.arch.persistence.room.Room

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 16/08/2017
 */
class DatabaseProvider(val application: Application) {
    fun provide(): Database {
        return Room.databaseBuilder(application.applicationContext, Database::class.java, "split-db").build()
    }
}
