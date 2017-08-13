package cz.petrkubes.split.ui.main.model.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import org.joda.time.DateTime

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 13/08/2017
 */

@Entity
class Payment(var paidBy: Int, var groupId: Int?) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var synced: Boolean = false
    var thingName: String? = null
    var amount: Int? = null
    var note: String? = null
    @Ignore // temporary
    var date: DateTime = DateTime.now()
    var parentDebtId: Int? = null
}
