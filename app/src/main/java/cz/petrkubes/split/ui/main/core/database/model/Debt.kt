package cz.petrkubes.split.ui.main.core.database.model

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.ForeignKey
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import cz.petrkubes.split.ui.main.core.database.AppDatabase

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 13/08/2017
 */
@Table(database = AppDatabase::class)
class Debt : BaseRXModel() {

    @PrimaryKey(autoincrement = true)
    var id: Int = 0

    @ForeignKey
    var paidBy: User? = null

    @ForeignKey
    var paidFor: User? = null

    @ForeignKey
    var group: Group? = null

    @Column
    var thingName: String? = null

    @Column
    var amount: Int? = null

    @ForeignKey
    var currency: Currency? = null

    @Column
    var note: String? = null

    @Column
    var date: Long = System.currentTimeMillis() / 1000

    @Column
    var parentDebtId: Int? = null
}
