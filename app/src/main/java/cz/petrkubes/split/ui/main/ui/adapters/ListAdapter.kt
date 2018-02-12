package cz.petrkubes.split.ui.main.ui.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 11/02/2018
 */
class ListAdapter<T: Any>(context: Context, private val originalList: List<T>, val layout: Int, val filterRule:((T, constraint: CharSequence) -> Boolean)? = null) : ArrayAdapter<Any>(context, android.R.layout.simple_dropdown_item_1line), Filterable {

    val tempList: MutableList<T> = if (filterRule == null) originalList as MutableList<T> else mutableListOf()
    private val customFilter: CustomFilter = CustomFilter()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: ViewHolder
        val retView: View

        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(parent?.context)
            val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, layout, parent, false)
            retView = binding.root
            viewHolder = ViewHolder(binding)
            viewHolder.bind(tempList[position])
            retView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
            viewHolder.bind(tempList[position])
            retView = viewHolder.binding.root
        }

        return retView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return getView(position, convertView, parent)
    }

    override fun getCount(): Int {
        return tempList.count()
    }

    override fun getItem(position: Int): T {
        return tempList[position]
    }

    override fun getFilter(): Filter {
        return customFilter
    }

    private inner class CustomFilter : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            if (filterRule != null) {
                tempList.clear()
                if (constraint != null) {
                    Log.d("asdf", constraint as String)
                    tempList.addAll(originalList)
                    tempList.retainAll {
                        filterRule.invoke(it, constraint)
                    }
                }
            }

            val results = FilterResults()
            results.values = tempList
            results.count = tempList.size
            return results
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
            notifyDataSetChanged()
        }
    }
}
