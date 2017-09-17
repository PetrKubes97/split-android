package cz.petrkubes.split.ui.main.repositories

import cz.petrkubes.split.ui.main.core.data.User
import cz.petrkubes.split.ui.main.core.database.Database
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 13/08/2017
 */
class UserRepository @Inject constructor(val database: Database) {

    fun saveFriend(user: User) {
        Single
                .fromCallable { database.userDao().insert(user) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    fun getFriends(): Flowable<List<User>> = database.userDao().getAll()
}
