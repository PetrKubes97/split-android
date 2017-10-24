package cz.petrkubes.split.ui.main.ui.payment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.FragmentPaymentsBinding
import cz.petrkubes.split.ui.main.ui.adapters.PaymentsAdapter

/**
* @author Petr Kubes <petr.kubes@applifting.cz>
* @since 25/07/2017
*/
class PaymentsFragment : android.support.v4.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val bindings: FragmentPaymentsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_payments, container, false)

        // Add data to listview
        val adapter: PaymentsAdapter = PaymentsAdapter(context, listOf(1, 2, 3))
        bindings.lvPayments.adapter = adapter

        return bindings.root
    }
}
