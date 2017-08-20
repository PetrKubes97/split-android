package cz.petrkubes.split.ui.main.di.modules

import android.app.Application
import cz.petrkubes.split.ui.main.core.database.DatabaseProvider
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 16/08/2017
 */
@Module
class CoreModule @Inject constructor(val application: Application) {

    @Provides
    @Singleton
    fun provideDatabase(): cz.petrkubes.split.ui.main.core.database.Database {
        return DatabaseProvider(application).provide()
    }

}
