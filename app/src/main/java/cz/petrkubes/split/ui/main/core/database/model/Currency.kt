package cz.petrkubes.split.ui.main.core.database.model

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import cz.petrkubes.split.ui.main.core.database.AppDatabase
import cz.petrkubes.split.ui.main.ui.adapters.Item

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 13/02/2018
 */
@Table(database = AppDatabase::class)
class Currency() : BaseRXModel(), Item {

    constructor(name: String, USDRate: Int) : this() {
        this.name = name
        this.USDRate = USDRate
    }

    @Column
    var name: String = "Unknown"

    @Column
    var USDRate: Int = 9999

    @PrimaryKey(autoincrement = true)
    var id: Int = 0

    override fun getItemName(): String {
        return name
    }
}
