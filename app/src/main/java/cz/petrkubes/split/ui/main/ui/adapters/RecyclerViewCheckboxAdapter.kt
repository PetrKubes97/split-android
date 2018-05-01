package cz.petrkubes.split.ui.main.ui.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.ItemCheckboxSimpleBinding

class RecyclerViewCheckboxAdapter(val list: List<Any>) : RecyclerView.Adapter<ViewHolderCheckbox>() {

    val checkedItemsList : MutableList<Any> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCheckbox {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemCheckboxSimpleBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_checkbox_simple, parent, false)

        return ViewHolderCheckbox(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolderCheckbox, position: Int) {
        holder.bind(list[position], checkedItemsList)
    }

}