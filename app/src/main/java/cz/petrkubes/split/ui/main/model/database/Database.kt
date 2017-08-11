package cz.petrkubes.split.ui.main.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import cz.petrkubes.split.ui.main.model.data.Friend

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 11/08/2017
 */
@Database(entities = arrayOf(Friend::class), version = 1)
abstract class Database : RoomDatabase() {
    abstract fun friendDao(): FriendDao
}
