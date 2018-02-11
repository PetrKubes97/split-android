package cz.petrkubes.split.ui.main.repositories

import com.raizlabs.android.dbflow.kotlinextensions.select
import com.raizlabs.android.dbflow.rx2.kotlinextensions.list
import com.raizlabs.android.dbflow.rx2.kotlinextensions.rx
import cz.petrkubes.split.ui.main.core.database.model.Payment
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 10/02/2018
 */
class PaymentRepository @Inject constructor() {

    fun insert(payment: Payment): Single<Boolean> = payment.save()

    fun getAll(): Single<List<Payment>> = select.from(Payment::class.java).rx().list
}
