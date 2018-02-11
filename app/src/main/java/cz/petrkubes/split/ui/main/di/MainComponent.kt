package cz.petrkubes.split.ui.main.di

import cz.petrkubes.split.ui.main.di.modules.CoreModule
import cz.petrkubes.split.ui.main.di.modules.RepositoriesModule
import cz.petrkubes.split.ui.main.ui.main.MainActivity
import cz.petrkubes.split.ui.main.ui.viewModels.FriendsViewModel
import cz.petrkubes.split.ui.main.ui.viewModels.GroupsViewModel
import cz.petrkubes.split.ui.main.ui.viewModels.PaymentsViewModel
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

    fun inject(viewModel: FriendsViewModel)

    fun inject(viewModel: GroupsViewModel)

    fun inject(viewModel: PaymentsViewModel)

    interface injectable {
        fun inject(mainComponent: MainComponent)
    }
}
