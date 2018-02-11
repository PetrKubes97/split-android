package cz.petrkubes.split.ui.main.ui.viewModels

import android.arch.lifecycle.ViewModel
import cz.petrkubes.split.ui.main.core.database.model.Payment
import cz.petrkubes.split.ui.main.di.MainComponent
import cz.petrkubes.split.ui.main.repositories.PaymentRepository
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
class PaymentsViewModel: ViewModel(), MainComponent.injectable {

    private val paymentsSubject: PublishSubject<List<Payment>> = PublishSubject.create()
    @Inject
    lateinit var paymentsRepository: PaymentRepository

    override fun inject(mainComponent: MainComponent) {
        mainComponent.inject(this)
    }


    fun getObservablePayments(): Observable<List<Payment>> {
        refreshPayments()
        return paymentsSubject
    }

    fun insertPayment(payment: Payment): Observable<Boolean> {

        val subject: PublishSubject<Boolean> = PublishSubject.create()

        paymentsRepository.insert(payment)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    refreshPayments()
                    subject.onNext(true)
                }

        return subject
    }


    // Refresh
    private fun refreshPayments() {
        paymentsRepository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy {
                    paymentsSubject.onNext(it)
                }
    }
}
