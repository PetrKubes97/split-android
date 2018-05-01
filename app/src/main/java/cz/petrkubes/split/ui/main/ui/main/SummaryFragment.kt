package cz.petrkubes.split.ui.main.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.petrkubes.split.R

/**
* @author Petr Kubes <petr.kubes@applifting.cz>
* @since 06/08/2017
*/
class SummaryFragment : android.support.v4.app.Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_payments, null)
    }
}
