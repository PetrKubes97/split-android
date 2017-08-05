package cz.petrkubes.split.ui.main.adapters

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import com.android.databinding.library.baseAdapters.BR

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 05/08/2017
 */
// The View Holder
class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun  bind(obj: Any) {
        binding.setVariable(BR.item, obj)
    }
}
