package cz.petrkubes.split.ui.main.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.tokenautocomplete.TokenCompleteTextView
import cz.petrkubes.split.R
import cz.petrkubes.split.ui.main.core.data.User

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/09/2017
 */
class ChipAutocompleteTextView : TokenCompleteTextView<User>{

    constructor(context: Context) : super(context)

    constructor(context: Context, attr: AttributeSet) : super(context, attr)

    override fun getViewForObject(user : User?): View {

        val chipView : TextView = LayoutInflater.from(context).inflate(R.layout.chip, parent as ViewGroup, false) as TextView

        if (user != null) {
            chipView.setText(user.name)
        } else {
            chipView.setText("null")
        }

        return chipView
    }

    override fun defaultObject(completionText: String?): User {
        // TODO database stuff
        if (completionText == null) {
            return User("unknown user")
        } else {
            return User(completionText)
        }
    }

}
