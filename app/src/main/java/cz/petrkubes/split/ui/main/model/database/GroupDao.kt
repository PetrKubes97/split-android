package cz.petrkubes.split.ui.main.model.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import cz.petrkubes.split.ui.main.model.data.Group

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 13/08/2017
 */
@Dao
interface GroupDao {

    @Insert
    fun insert(group: Group)
}
