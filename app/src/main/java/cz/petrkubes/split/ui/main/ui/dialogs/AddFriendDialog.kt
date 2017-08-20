package cz.petrkubes.split.ui.main.ui.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.databinding.DataBindingUtil
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.DialogAddFriendBinding
import cz.petrkubes.split.ui.main.core.data.User
import cz.petrkubes.split.ui.main.ui.activities.main.MainActivityViewModel

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 11/08/2017
 */
class AddFriendDialog(activity: Activity, val viewModel: MainActivityViewModel) {

    val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
    val binding: DialogAddFriendBinding = DataBindingUtil.inflate(activity.layoutInflater, R.layout.dialog_add_friend, null, false)
    var dialog: AlertDialog

    init {
        builder.setView(binding.root)
        builder.setPositiveButton("Add", {dialog, i ->
            viewModel.saveFriend(User("Pert"))
        })

        dialog = builder.create()
    }

    fun show() {
        dialog.show()
    }
}
