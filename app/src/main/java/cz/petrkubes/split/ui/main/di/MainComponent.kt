package cz.petrkubes.split.ui.main.di

import cz.petrkubes.split.ui.main.di.modules.CoreModule
import cz.petrkubes.split.ui.main.di.modules.RepositoriesModule
import cz.petrkubes.split.ui.main.repositories.UserRepository
import cz.petrkubes.split.ui.main.ui.activities.main.MainActivity
import cz.petrkubes.split.ui.main.ui.activities.main.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 16/08/2017
 */
@Singleton
@Component(modules = arrayOf(CoreModule::class, RepositoriesModule::class))
interface MainComponent {

    fun inject(activity: MainActivity)

    fun inject(viewModel: MainActivityViewModel)

    fun getRepo(): UserRepository

    interface injectable {
        fun inject(mainComponent: MainComponent)
    }
}