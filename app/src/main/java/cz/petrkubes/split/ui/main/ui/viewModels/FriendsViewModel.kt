package cz.petrkubes.split.ui.main.ui.viewModels

import android.arch.lifecycle.ViewModel
import cz.petrkubes.split.ui.main.core.database.model.Group
import cz.petrkubes.split.ui.main.core.database.model.User
import cz.petrkubes.split.ui.main.di.MainComponent
import cz.petrkubes.split.ui.main.repositories.UserRepository
import io.reactivex.Observable
import io.reactivex.Single
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

    private var groupFriendsSubject: PublishSubject<List<User>> = PublishSubject.create()
    private var allFriendsSubject: PublishSubject<List<User>> = PublishSubject.create()

    override fun inject(mainComponent: MainComponent) {
        mainComponent.inject(this)
    }

    fun getFriendsInGroup(groupId: Int): Observable<List<User>> {
        refreshFriends(groupId = groupId)
        return groupFriendsSubject
    }

    fun getAllFriends(): Observable<List<User>> {
        refreshFriends(true)
        return allFriendsSubject
    }

    fun insertFriend(user: User) {
        userRepository.insert(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    refreshFriends(true)
                }
    }

    // Todo make nicer rx
    fun insertRelations(group: Group, users: List<User>) {
        getAllFriends()
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

    fun refreshFriends(all: Boolean = false, groupId: Int = 0) {
        val friendsSingle: Single<List<User>>

        if (all || groupId == 0)
            friendsSingle = userRepository.getAll()
        else
            friendsSingle = userRepository.getByGroupId(groupId)

        friendsSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    if (all)
                        allFriendsSubject.onNext(it)
                    groupFriendsSubject.onNext(it)
                }
    }
}

