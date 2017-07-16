package cz.petrkubes.split.ui.main.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import cz.petrkubes.split.R
import cz.petrkubes.split.ui.main.tabs.expenses.ExpensesFragment
import cz.petrkubes.split.ui.main.tabs.expenses.PeopleFragment
import cz.petrkubes.split.ui.main.tabs.expenses.SummaryFragment


/**
* Created by Petr Kubes on 15.7.17.
*/
class FragmentsAdapter(fm: FragmentManager, var context: Context) : FragmentPagerAdapter(fm) {

    override fun getItem(i: Int): Fragment {
        when (i) {
            0 -> return ExpensesFragment()
            1 -> return PeopleFragment()
            else -> return SummaryFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            0 -> return context.getString(R.string.expenses)
            0 -> return context.getString(R.string.people)
            else -> return context.getString(R.string.summary)
        }
    }
}
