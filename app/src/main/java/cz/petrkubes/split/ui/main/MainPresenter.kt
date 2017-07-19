package cz.petrkubes.split.ui.main

import android.annotation.SuppressLint
import android.view.MenuItem
import android.view.View
import cz.petrkubes.split.R

/**
 * @author Petr Kubes <petr.kubes@applifting.com>
 * @since 19/07/2017.
 */
class MainPresenter(var mainActivity: MainActivity) {

    fun onAddDebtButtonClick(view: View) {
        mainActivity.showSnackbar(view)
    }

    @SuppressLint("PrivateResource")
    fun onNavigationItemSelected(item: MenuItem) {
        val id = item.itemId
        if (id % 2 == 0)
            mainActivity.changeAppColor(R.color.material_blue_grey_950)
        else
            mainActivity.changeAppColor(R.color.colorPrimary)

        mainActivity.closeDrawer()
    }
}
