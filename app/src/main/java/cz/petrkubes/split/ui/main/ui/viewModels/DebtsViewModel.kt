package cz.petrkubes.split.ui.main.ui.viewModels

import android.arch.lifecycle.ViewModel
import cz.petrkubes.split.ui.main.core.database.model.Debt
import cz.petrkubes.split.ui.main.di.MainComponent
import cz.petrkubes.split.ui.main.repositories.DebtRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 10/02/2018
 */
class DebtsViewModel : ViewModel(), MainComponent.injectable {

    private val debtsSubject: PublishSubject<List<Debt>> = PublishSubject.create()
    @Inject
    lateinit var debtsRepository: DebtRepository

    override fun inject(mainComponent: MainComponent) {
        mainComponent.inject(this)
    }


    fun getDebts(): Observable<List<Debt>> {
        refreshDebts()
        return debtsSubject
    }

    fun insertDebt(debt: Debt): Observable<Boolean> {

        val subject: PublishSubject<Boolean> = PublishSubject.create()

        debtsRepository.insert(debt)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    refreshDebts()
                    subject.onNext(true)
                }

        return subject
    }


    // Refresh
    private fun refreshDebts() {
        debtsRepository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    debtsSubject.onNext(it)
                }
    }
}
