package cz.petrkubes.split.ui.main.ui.groups

import android.app.AlertDialog
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import com.tokenautocomplete.FilteredArrayAdapter
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.ActivityMainBinding
import cz.petrkubes.split.databinding.DialogCreateGroupBinding
import cz.petrkubes.split.ui.main.core.database.model.Group
import cz.petrkubes.split.ui.main.core.database.model.User
import cz.petrkubes.split.ui.main.ui.App
import cz.petrkubes.split.ui.main.ui.ViewModelFactory
import cz.petrkubes.split.ui.main.ui.viewModels.FriendsViewModel
import cz.petrkubes.split.ui.main.ui.viewModels.GroupsViewModel
import java.util.*


/**
 * @author Petr Kubes <petr.kubes@applifting.cz>
 * @since 17/09/2017
 */
class CreateGroupDialog(activity: AppCompatActivity, activityBinding: ActivityMainBinding) {

    private val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
    private val binding: DialogCreateGroupBinding = DataBindingUtil.inflate(activity.layoutInflater, R.layout.dialog_create_group, null, false)
    private val dialog: AlertDialog

    init {

        val friendsViewModel = ViewModelProviders.of(activity, ViewModelFactory(activity.application as App)).get(FriendsViewModel::class.java)
        val groupsViewModel = ViewModelProviders.of(activity, ViewModelFactory(activity.application as App)).get(GroupsViewModel::class.java)

        val people = ArrayList<User>()
        val adapter = object : FilteredArrayAdapter<User>(activity, android.R.layout.simple_list_item_1, people) {
            override fun keepObject(obj: User, mask: String): Boolean =
                    obj.email?.contains(mask) ?: false || obj.name.contains(mask)
        }

        binding.etChipAutocomplete.setAdapter(adapter)

        // Load friends
        friendsViewModel.getAllFriends().subscribe {
            people.clear()
            people.addAll(it)
            adapter.notifyDataSetChanged()
        }

        builder.setView(binding.root)
                .setTitle("Create a group")
                .setPositiveButton("Add", { dialog, i ->
                    // Save the group
                    val group = Group()
                    group.name = binding.etGroupName.text.toString()
                    groupsViewModel.insertGroup(group).subscribe {
                        // Add user <-> table relation
                        friendsViewModel.insertRelations(group, binding.etChipAutocomplete.objects)

                    }

                    binding.etChipAutocomplete.setText("")
                    binding.etGroupName.setText("")

                    activityBinding.drawerLayout.closeDrawers()
                })

        dialog = builder.create()
    }

    fun show() {
        dialog.show()
    }
}
