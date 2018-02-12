package cz.petrkubes.split.ui.main.ui.payment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.ActivityDebtBinding
import cz.petrkubes.split.ui.main.core.database.model.Group
import cz.petrkubes.split.ui.main.core.database.model.User
import cz.petrkubes.split.ui.main.ui.App
import cz.petrkubes.split.ui.main.ui.ViewModelFactory
import cz.petrkubes.split.ui.main.ui.adapters.ListAdapter
import cz.petrkubes.split.ui.main.ui.viewModels.FriendsViewModel
import cz.petrkubes.split.ui.main.ui.viewModels.GroupsViewModel
import cz.petrkubes.split.ui.main.ui.viewModels.PaymentsViewModel


class PaymentActivity : AppCompatActivity() {

    lateinit var binding: ActivityDebtBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debt)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_debt)

        // Set up action bar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up view model
        val groupsViewModel: GroupsViewModel = ViewModelProviders.of(this, ViewModelFactory(application as App)).get(GroupsViewModel::class.java)
        val friendsViewModel: FriendsViewModel = ViewModelProviders.of(this, ViewModelFactory(application as App)).get(FriendsViewModel::class.java)
        val paymentsViewModel: PaymentsViewModel = ViewModelProviders.of(this, ViewModelFactory(application as App)).get(PaymentsViewModel::class.java)

        val friends: MutableList<User> = mutableListOf()
        val adapterFriends = ListAdapter(this, friends, R.layout.item_friend_simple, { user, constraint -> user.name.contains(constraint) })

        binding.actvWho.setAdapter(adapterFriends)
        binding.actvWho.threshold = 0

        // Set up debtor
        binding.rdioFriend.isChecked = groupsViewModel.currentGroupId == 0
        // Show group spinner or a friend
        fun setUpWhom() {
            if (binding.rdioFriend.isChecked) {
                binding.actvForWhom.visibility = View.VISIBLE
                binding.spnForGroup.visibility = View.GONE
            } else {
                binding.actvForWhom.visibility = View.GONE
                binding.spnForGroup.visibility = View.VISIBLE
            }
        }

        binding.rdioGrpForWhom.setOnCheckedChangeListener { _, _ ->
            setUpWhom()
        }
        setUpWhom()

        // Set up for whom auto complete text view
        binding.actvForWhom.setAdapter(adapterFriends)
        binding.actvForWhom.threshold = 0

        friendsViewModel.getAllFriends().subscribe {
            friends.clear()
            friends.addAll(it)
            adapterFriends.notifyDataSetChanged()
        }

        // Set up groups spinner
        val groups: MutableList<Group> = mutableListOf()
        val groupsAdapter = ListAdapter(this, groups, R.layout.item_group_simple)

        binding.spnForGroup.adapter = groupsAdapter

        groupsViewModel.getAllGroups().subscribe {
            groups.clear()
            groups.addAll(it)
            groupsAdapter.notifyDataSetChanged()
        }
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return super.onNavigateUp()
    }

    fun validateAndSaveTheDebt() {

    }


}
