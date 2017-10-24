package cz.petrkubes.split.ui.main.core.database.model

import org.joda.time.DateTime

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 13/08/2017
 */

class Payment(var paidBy: Int, var groupId: Int?) {

    var id: Int = 0
    var synced: Boolean = false
    var thingName: String? = null
    var amount: Int? = null
    var note: String? = null


    var date: DateTime = DateTime.now()
    var parentDebtId: Int? = null
}
