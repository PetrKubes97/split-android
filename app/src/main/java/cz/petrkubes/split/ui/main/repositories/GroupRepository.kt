package cz.petrkubes.split.ui.main.repositories

import com.raizlabs.android.dbflow.kotlinextensions.select
import com.raizlabs.android.dbflow.rx2.kotlinextensions.list
import com.raizlabs.android.dbflow.rx2.kotlinextensions.rx
import cz.petrkubes.split.ui.main.core.database.model.Group
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 23/09/2017
 */
class GroupRepository @Inject constructor() {

    fun insert(group: Group): Single<Boolean> = group.save()

    fun getAll(): Single<List<Group>> = select.from(Group::class.java).rx().list
}
