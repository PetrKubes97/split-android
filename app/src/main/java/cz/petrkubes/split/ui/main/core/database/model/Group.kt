package cz.petrkubes.split.ui.main.core.database.model

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import cz.petrkubes.split.ui.main.core.database.AppDatabase
import cz.petrkubes.split.ui.main.ui.adapters.Item

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 13/08/2017
 */

@Table(database = AppDatabase::class)
class Group : BaseRXModel(), Item {

    @PrimaryKey(autoincrement = true)
    var id: Int = 0

    @Column
    var name: String = "unknown"

    @Column
    var isSynced: Boolean = false

    // UI params
    var isSelected: Boolean = false

    override fun getItemName(): String {
        return name
    }
}
