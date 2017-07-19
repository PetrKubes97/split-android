package cz.petrkubes.split.ui.main.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import cz.petrkubes.split.R


/**
 * @author Petr Kubes <petr.kubes@applifting.com>
 * @since 19/07/2017.
 */

class NavMenuItem constructor(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    @BindView(R.id.nav_item_ic)
    lateinit var ivIcon: ImageView

    @BindView(R.id.nav_item_tv)
    lateinit var tvText: TextView

    init {
        // Obtain necessary attributes

        val a = context.obtainStyledAttributes(attrs, R.styleable.NavMenuItem, 0, 0)
        val text = a.getString(R.styleable.NavMenuItem_text)
        val icon = a.getDrawable(R.styleable.NavMenuItem_icon)
        a.recycle()

        // Inflate layout
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.nav_menu_item, this, true)
        ButterKnife.bind(this)

        // Set attributes
        tvText.text = text
        ivIcon.setImageDrawable(icon)
    }

}

