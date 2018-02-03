package cz.petrkubes.split.ui.main.ui.friends

import android.arch.lifecycle.ViewModel
import cz.petrkubes.split.ui.main.core.database.model.Group
import cz.petrkubes.split.ui.main.core.database.model.User
import cz.petrkubes.split.ui.main.di.MainComponent
import cz.petrkubes.split.ui.main.repositories.UserRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 06/08/2017
 */
class FriendsViewModel : ViewModel(), MainComponent.injectable {

    @Inject
    lateinit var userRepository: UserRepository

    private var friendsSubject: PublishSubject<List<User>> = PublishSubject.create()

    override fun inject(mainComponent: MainComponent) {
        mainComponent.inject(this)
    }


    fun getObservableFriends(): Observable<List<User>> {
        refreshFriends()
        return friendsSubject
    }

    fun insertFriend(user: User) {
        userRepository.insert(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    refreshFriends()
                }
    }

    fun insertRelations(group: Group, users: List<User>) {
        getObservableFriends()
                .subscribe { savedUsers ->
                    val usersInDatabase: PublishSubject<User> = PublishSubject.create()

                    usersInDatabase
                            .subscribeOn(Schedulers.io())
                            .subscribe({ user ->
                                userRepository.insertRelation(user, group)
                            })

                    users.forEach {
                        if (savedUsers.contains(it)) {
                            usersInDatabase.onNext(it)
                        } else {
                            userRepository.insert(it).subscribeBy { succeeded ->
                                if (succeeded) {
                                    usersInDatabase.onNext(it)
                                }
                            }
                        }
                    }
                }
    }


    // Refresh
    private fun refreshFriends() {
        userRepository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    friendsSubject.onNext(it)
                }
    }
}

