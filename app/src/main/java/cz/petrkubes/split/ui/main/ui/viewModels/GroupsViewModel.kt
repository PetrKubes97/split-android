package cz.petrkubes.split.ui.main.ui.viewModels

import android.arch.lifecycle.ViewModel
import cz.petrkubes.split.ui.main.core.database.model.Group
import cz.petrkubes.split.ui.main.di.MainComponent
import cz.petrkubes.split.ui.main.repositories.GroupRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 28/09/2017
 */
class GroupsViewModel : ViewModel(), MainComponent.injectable {

    private val groupsSubject: PublishSubject<List<Group>> = PublishSubject.create()
    var currentGroupId: Int = 0
        set(value) {
            field = value
            refreshGroups()
        }

    override fun inject(mainComponent: MainComponent) {
        mainComponent.inject(this)
    }

    @Inject
    lateinit var groupRepository: GroupRepository

    fun getAllGroups(): Observable<List<Group>> {
        refreshGroups()
        return groupsSubject
    }

    fun insertGroup(group: Group): Observable<Boolean> {

        val subject: PublishSubject<Boolean> = PublishSubject.create()

        groupRepository.insert(group)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    refreshGroups()
                    subject.onNext(true)
                }

        return subject
    }


    // Refresh
    private fun refreshGroups() {
        groupRepository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    // Mark selected
                    it.forEach {
                        if (it.id == currentGroupId) it.isSelected = true
                    }

                    groupsSubject.onNext(it)
                }
    }
}
