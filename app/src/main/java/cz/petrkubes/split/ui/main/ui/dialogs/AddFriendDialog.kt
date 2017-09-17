package cz.petrkubes.split.ui.main.ui.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.databinding.DataBindingUtil
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.ActivityMainBinding
import cz.petrkubes.split.databinding.DialogAddFriendBinding
import cz.petrkubes.split.ui.main.core.data.User
import cz.petrkubes.split.ui.main.ui.activities.main.MainActivityViewModel

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 11/08/2017
 */
class AddFriendDialog(activity: Activity, activityBinding: ActivityMainBinding, viewModel: MainActivityViewModel) {

    private val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
    private val binding: DialogAddFriendBinding = DataBindingUtil.inflate(activity.layoutInflater, R.layout.dialog_add_friend, null, false)
    private val dialog: AlertDialog

    init {
        builder.setView(binding.root)
        builder.setPositiveButton("Add", {dialog, i ->
            viewModel.saveFriend(User(binding.textFriendName.text.toString()))
            binding.textFriendName.setText("")
            activityBinding.drawerLayout.closeDrawers()
        })

        dialog = builder.create()
    }

    fun show() {
        dialog.show()
    }
}
