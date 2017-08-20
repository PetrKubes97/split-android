package cz.petrkubes.split.ui.main.ui.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import cz.petrkubes.split.ui.main.core.data.User


/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 05/08/2017
 */
class RecyclerViewAdapter(var data: MutableList<User>, val itemLayout: Int) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.getContext())
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, itemLayout, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder != null) {
            holder.bind(data.get(position))
        }
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }


}
