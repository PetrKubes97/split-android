package cz.petrkubes.split.ui.main.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.ItemPaymentBinding


/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 25/07/2017
 */
class PaymentsAdapter(context: Context, data: List<Any>) : ArrayAdapter<Any>(context, 0, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val binding: ItemPaymentBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_payment, parent, false)
        return binding.root
    }
}
