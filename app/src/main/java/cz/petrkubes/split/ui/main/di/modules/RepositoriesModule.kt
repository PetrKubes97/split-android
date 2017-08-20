package cz.petrkubes.split.ui.main.di.modules

import cz.petrkubes.split.ui.main.core.database.Database
import cz.petrkubes.split.ui.main.repositories.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 16/08/2017
 */
@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideUserRepository(database: Database): UserRepository {
        return UserRepository(database)
    }
}
