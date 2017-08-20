package cz.petrkubes.split.ui.main.core.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import cz.petrkubes.split.ui.main.core.data.DebtToUserRelation

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 13/08/2017
 */
@Dao
interface DebtToUserRelationDao {

    @Insert
    fun insert(debtToUserRelation: DebtToUserRelation)

}
