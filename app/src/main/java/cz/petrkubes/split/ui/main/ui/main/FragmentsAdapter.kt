package cz.petrkubes.split.ui.main.ui.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import cz.petrkubes.split.R
import cz.petrkubes.split.ui.main.ui.friends.FriendsFragment
import cz.petrkubes.split.ui.main.ui.debt.DebtsFragment


/**
* Created by Petr Kubes on 15.7.17.
*/
class FragmentsAdapter(fm: FragmentManager, var context: Context) : FragmentPagerAdapter(fm) {

    override fun getItem(i: Int): Fragment {
        when (i) {
            0 -> return DebtsFragment()
            1 -> return FriendsFragment()
            else -> return SummaryFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            0 -> return context.getString(R.string.expenses)
            1 -> return context.getString(R.string.users)
            else -> return context.getString(R.string.summary)
        }
    }
}
