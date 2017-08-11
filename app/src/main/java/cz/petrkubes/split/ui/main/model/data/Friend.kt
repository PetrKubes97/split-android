package cz.petrkubes.split.ui.main.model.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 05/08/2017
 */

@Entity
class Friend(name: String) {
    @PrimaryKey (autoGenerate = true)
    public var id: Int = 0
    public var name: String = ""
}
