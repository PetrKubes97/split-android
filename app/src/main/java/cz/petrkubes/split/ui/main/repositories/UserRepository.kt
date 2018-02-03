package cz.petrkubes.split.ui.main.repositories

import com.raizlabs.android.dbflow.kotlinextensions.select
import com.raizlabs.android.dbflow.rx2.kotlinextensions.list
import com.raizlabs.android.dbflow.rx2.kotlinextensions.rx
import cz.petrkubes.split.ui.main.core.database.model.Group
import cz.petrkubes.split.ui.main.core.database.model.User
import cz.petrkubes.split.ui.main.core.database.model.User_Group
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 13/08/2017
 */
class UserRepository @Inject constructor() {

    fun insert(user: User): Single<Boolean> = user.save()

    fun getAll(): Single<List<User>> = select.from(User::class.java).rx().list

    fun insertRelation(user: User, group: Group): Boolean {
        val userGroup = User_Group()
        userGroup.group = group
        userGroup.user = user
        return userGroup.save()
    }
}
