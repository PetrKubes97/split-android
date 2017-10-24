package cz.petrkubes.split.ui.main.di

import cz.petrkubes.split.ui.main.di.modules.CoreModule
import cz.petrkubes.split.ui.main.di.modules.RepositoriesModule
import cz.petrkubes.split.ui.main.ui.friends.FriendsViewModel
import cz.petrkubes.split.ui.main.ui.groups.GroupsViewModel
import cz.petrkubes.split.ui.main.ui.main.MainActivity
import cz.petrkubes.split.ui.main.ui.main.MainActivityViewModel
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

    fun inject(viewModel: FriendsViewModel)

    fun inject(viewModel: GroupsViewModel)

    interface injectable {
        fun inject(mainComponent: MainComponent)
    }
}
