package cz.petrkubes.split.ui.main.model.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 05/08/2017
 */

@Entity
class User(var name: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var synced: Boolean = false
    var email: String? = null
    var isCurrentUser = false
}
