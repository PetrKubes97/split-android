package cz.petrkubes.split.ui.main.core.database.model

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.ManyToMany
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import cz.petrkubes.split.ui.main.core.database.AppDatabase
import cz.petrkubes.split.ui.main.ui.adapters.Item

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 05/08/2017
 */
@ManyToMany(referencedTable = Group::class)
@Table(database = AppDatabase::class)
class User : BaseRXModel, Item {

    constructor() : super() {
        this.name = "unknown"
    }

    constructor(name: String) : super() {
        this.name = name
    }

    @PrimaryKey(autoincrement = true)
    var id: Int = 0

    @Column
    var name: String

    @Column
    var isSynced: Boolean = false

    @Column
    var isCurrentUser: Boolean = false

    @Column
    var email: String? = null

    override fun toString(): String = name

    override fun getItemName(): String {
        return name
    }
}
