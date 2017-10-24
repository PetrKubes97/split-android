package cz.petrkubes.split.ui.main.ui.main

import android.arch.lifecycle.ViewModel
import cz.petrkubes.split.ui.main.core.database.model.Group
import cz.petrkubes.split.ui.main.di.MainComponent
import cz.petrkubes.split.ui.main.repositories.GroupRepository
import javax.inject.Inject

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 06/08/2017
 */
class MainActivityViewModel : ViewModel(), MainComponent.injectable {

    @Inject
    lateinit var groupRepository: GroupRepository

    override fun inject(mainComponent: MainComponent) {
        mainComponent.inject(this)
    }

    fun saveGroup(group: Group) {
        groupRepository.insert(group)
    }
}
