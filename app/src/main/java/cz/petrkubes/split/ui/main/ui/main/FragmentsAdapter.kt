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
        return when (i) {
            0 -> DebtsFragment()
            1 -> FriendsFragment()
            else -> SummaryFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> context.getString(R.string.expenses)
            1 -> context.getString(R.string.users)
            else -> context.getString(R.string.summary)
        }
    }
}
