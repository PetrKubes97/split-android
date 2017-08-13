package cz.petrkubes.split.ui.main.model.data

import android.arch.persistence.room.Entity

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 13/08/2017
 */
@Entity(primaryKeys = arrayOf("userId", "debtId"))
class DebtToUserRelation(var userId: Int, var debtId: Int) {

}
