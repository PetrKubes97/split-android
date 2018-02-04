package cz.petrkubes.split.ui.main.repositories

import com.raizlabs.android.dbflow.kotlinextensions.select
import com.raizlabs.android.dbflow.rx2.kotlinextensions.list
import com.raizlabs.android.dbflow.rx2.kotlinextensions.rx
import cz.petrkubes.split.ui.main.core.database.model.*
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

    fun getByGroupId(groupId: Int): Single<List<User>> {
        return select.from(User::class.java).innerJoin(User_Group::class.java)
                .on(User_Table.id.`is`(User_Group_Table.user_id))
                .where(User_Group_Table.group_id.`is`(groupId))
                .rx()
                .list
    }
}
