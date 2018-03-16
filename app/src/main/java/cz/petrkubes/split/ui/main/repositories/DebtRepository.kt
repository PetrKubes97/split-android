package cz.petrkubes.split.ui.main.repositories

import com.raizlabs.android.dbflow.kotlinextensions.select
import com.raizlabs.android.dbflow.rx2.kotlinextensions.list
import com.raizlabs.android.dbflow.rx2.kotlinextensions.rx
import cz.petrkubes.split.ui.main.core.database.model.Debt
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 10/02/2018
 */
class DebtRepository @Inject constructor() {

    fun insert(debt: Debt): Single<Boolean> = debt.save()

    fun getAll(): Single<List<Debt>> = select.from(Debt::class.java).rx().list
}
