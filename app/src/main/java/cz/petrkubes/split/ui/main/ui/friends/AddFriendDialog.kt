package cz.petrkubes.split.ui.main.ui.friends

import android.app.AlertDialog
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.ActivityMainBinding
import cz.petrkubes.split.databinding.DialogAddFriendBinding
import cz.petrkubes.split.ui.main.core.database.model.User
import cz.petrkubes.split.ui.main.ui.App
import cz.petrkubes.split.ui.main.ui.ViewModelFactory

/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 11/08/2017
 */
class AddFriendDialog(activity: AppCompatActivity, activityBinding: ActivityMainBinding) {

    private val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
    private val binding: DialogAddFriendBinding = DataBindingUtil.inflate(activity.layoutInflater, R.layout.dialog_add_friend, null, false)
    private val dialog: AlertDialog
    private val viewModel: FriendsViewModel = ViewModelProviders.of(activity, ViewModelFactory(activity.application as App)).get(FriendsViewModel::class.java)

    init {
        builder.setView(binding.root)
        builder.setPositiveButton("Add", {dialog, i ->
            val user = User(binding.textFriendName.text.toString())
            viewModel.insertFriend(user)
            binding.textFriendName.setText("")
            activityBinding.drawerLayout.closeDrawers()
        })

        dialog = builder.create()
    }

    fun show() {
        dialog.show()
    }
}
