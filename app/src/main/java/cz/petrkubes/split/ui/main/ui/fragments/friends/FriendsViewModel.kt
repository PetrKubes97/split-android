package cz.petrkubes.split.ui.main.ui.fragments.friends

import android.arch.lifecycle.ViewModel
import cz.petrkubes.split.ui.main.core.data.User
import cz.petrkubes.split.ui.main.di.MainComponent
import cz.petrkubes.split.ui.main.repositories.UserRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 06/08/2017
 */
class FriendsViewModel : ViewModel(), MainComponent.injectable {

    @Inject
    lateinit var userRepository: UserRepository

    override fun inject(mainComponent: MainComponent) {
        mainComponent.inject(this)
    }

    fun observableFriends(): Flowable<List<User>> = userRepository.getFriends()
}

