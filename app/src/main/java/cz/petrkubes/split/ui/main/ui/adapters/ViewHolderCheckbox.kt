package cz.petrkubes.split.ui.main.ui.adapters

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import com.android.databinding.library.baseAdapters.BR
import cz.petrkubes.split.databinding.ItemCheckboxSimpleBinding

class ViewHolderCheckbox(var binding: ItemCheckboxSimpleBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(obj: Any, checkedItemsList: MutableList<Any>) {
        binding.setVariable(BR.item, obj)
        binding.checkbox.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked) {
                checkedItemsList.add(obj)
            } else if (checkedItemsList.contains(obj)) {
                checkedItemsList.remove(obj)
            }
        }
    }
}