package cz.petrkubes.split.ui.main.ui.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.databinding.DataBindingUtil
import com.tokenautocomplete.FilteredArrayAdapter
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.ActivityMainBinding
import cz.petrkubes.split.databinding.DialogCreateGroupBinding
import cz.petrkubes.split.ui.main.core.data.User
import cz.petrkubes.split.ui.main.ui.activities.main.MainActivityViewModel




/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/09/2017
 */
class CreateGroupDialog(activity: Activity, activityBinding: ActivityMainBinding, viewModel: MainActivityViewModel) {

    private val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
    private val binding: DialogCreateGroupBinding = DataBindingUtil.inflate(activity.layoutInflater, R.layout.dialog_create_group, null, false)
    private val dialog: AlertDialog

    init {
        builder.setView(binding.root)
                .setTitle("Create a group")
                .setPositiveButton("Add", { dialog, i ->
                    //viewModel.saveFriend(User(binding.textFriendName.text.toString()))
                    //binding.textFriendName.setText("")
                    

                    activityBinding.drawerLayout.closeDrawers()
                })

        val people = arrayOf<User>(User("asdf"), User("fdsa"))
        val adapter = object : FilteredArrayAdapter<User>(activity, android.R.layout.simple_list_item_1, people) {
            override fun keepObject(obj: User, mask: String): Boolean =
                    obj.email?.contains(mask) ?: false || obj.name.contains(mask)
        }

        binding.etChipAutocomplete.setAdapter(adapter)

        dialog = builder.create()
    }

    fun show() {
        dialog.show()
    }
}
