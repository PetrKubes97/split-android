package cz.petrkubes.split.ui.main.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import cz.petrkubes.split.ui.main.model.data.DebtToUserRelation
import cz.petrkubes.split.ui.main.model.data.Group
import cz.petrkubes.split.ui.main.model.data.Payment
import cz.petrkubes.split.ui.main.model.data.User

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 11/08/2017
 */
@Database(entities = arrayOf(User::class, Payment::class, Group::class, DebtToUserRelation::class), version = 2)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun PaymentDao(): PaymentDao
    abstract fun GroupDao(): GroupDao
    abstract fun DebtToUserRelationDao(): DebtToUserRelationDao
}
