package cz.petrkubes.split.ui.main.ui.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 05/08/2017
 */
class RecyclerViewAdapter(var data: List<Any>, private val itemLayout: Int, val listener: (item: Any) -> Unit) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, itemLayout, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder != null) {
            holder.bind(data.get(position), listener)
        }
    }

    override fun getItemCount(): Int = data.size
}
