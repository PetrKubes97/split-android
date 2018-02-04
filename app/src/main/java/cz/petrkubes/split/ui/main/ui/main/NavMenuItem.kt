package cz.petrkubes.split.ui.main.ui.main

import android.content.Context
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.NavMenuItemBinding


/**
 * @author Petr Kubes <petr.kubes@applifting.com>
 * @since 19/07/2017.
 */

class NavMenuItem constructor(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    private val binding: NavMenuItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.nav_menu_item, this, true)

    init {
        // Obtain attributes
        val a = context.obtainStyledAttributes(attrs, R.styleable.NavMenuItem, 0, 0)
        val text = a.getString(R.styleable.NavMenuItem_text)
        val icon = a.getDrawable(R.styleable.NavMenuItem_icon)
        a.recycle()

        binding.textView.text = text
        binding.icon.setImageDrawable(icon)


    }

    override fun setOnClickListener(l: OnClickListener?) {
        binding.root.setOnClickListener(l)
    }
}

