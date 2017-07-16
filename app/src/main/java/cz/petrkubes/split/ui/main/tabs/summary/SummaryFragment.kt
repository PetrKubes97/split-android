package cz.petrkubes.split.ui.main.tabs.expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.petrkubes.split.R

/**
 * Created by Petr Kubes on 15.7.17.
 */
class SummaryFragment : android.support.v4.app.Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (inflater != null) {
            return inflater.inflate(R.layout.fragment_expenses, null)
        } else {
            return super.onCreateView(inflater, container, savedInstanceState)
        }
    }
}
