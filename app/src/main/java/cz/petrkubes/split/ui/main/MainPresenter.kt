package cz.petrkubes.split.ui.main

import android.view.MenuItem
import android.view.View
import cz.petrkubes.split.R

/**
 * Created by Petr Kubes on 10.7.17.
 */
class MainPresenter(var mainActivity: MainActivity) {

    fun onAddDebtButtonClick(view: View) {
        mainActivity.showSnackbar(view)
    }

    fun onNavigationItemSelected(item: MenuItem) {
        val id = item.itemId
        if (id % 2 == 0)
            mainActivity.changeAppColor(R.color.material_blue_grey_950)
        else
            mainActivity.changeAppColor(R.color.colorPrimary)

        mainActivity.closeDrawer()
    }
}
