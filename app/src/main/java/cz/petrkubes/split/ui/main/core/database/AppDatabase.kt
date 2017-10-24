package cz.petrkubes.split.ui.main.core.database

import com.raizlabs.android.dbflow.annotation.Database

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 24/09/2017
 */
@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION, generatedClassSeparator = "_")
object AppDatabase {
    const val NAME: String = "split"
    const val VERSION: Int = 6
}
