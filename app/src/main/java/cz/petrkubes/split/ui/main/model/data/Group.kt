package cz.petrkubes.split.ui.main.model.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 13/08/2017
 */
@Entity
class Group(var name: String) {
    @PrimaryKey (autoGenerate = true)
    var id: Int = 0
    var synced: Boolean = false
}
