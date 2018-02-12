package cz.petrkubes.split.ui.main.ui.main

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.ActivityMainBinding
import cz.petrkubes.split.ui.main.core.database.model.Group
import cz.petrkubes.split.ui.main.ui.App
import cz.petrkubes.split.ui.main.ui.ViewModelFactory
import cz.petrkubes.split.ui.main.ui.adapters.RecyclerViewAdapter
import cz.petrkubes.split.ui.main.ui.friends.AddFriendDialog
import cz.petrkubes.split.ui.main.ui.groups.CreateGroupDialog
import cz.petrkubes.split.ui.main.ui.payment.PaymentActivity
import cz.petrkubes.split.ui.main.ui.viewModels.FriendsViewModel
import cz.petrkubes.split.ui.main.ui.viewModels.GroupsViewModel
import cz.petrkubes.split.ui.main.util.debtRequestcode

class MainActivity : AppCompatActivity() {

    var lifecycleRegistry = LifecycleRegistry(this)
    lateinit var fragmentsAdapter: FragmentsAdapter

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val groupsViewModel: GroupsViewModel = ViewModelProviders.of(this, ViewModelFactory(application as App)).get(GroupsViewModel::class.java)
        val friendsViewModel: FriendsViewModel = ViewModelProviders.of(this, ViewModelFactory(application as App)).get(FriendsViewModel::class.java)

        // Set up action bar
        setSupportActionBar(binding.toolbar)

        // Set up drawer
        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout,binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)

        // Groups list
        val groups: MutableList<Group> = mutableListOf()
        val groupsAdapter = RecyclerViewAdapter(groups, R.layout.item_nav_menu, {
            groupsViewModel.currentGroupId = (it as Group).id
            friendsViewModel.refreshFriends(groupId = groupsViewModel.currentGroupId)
        })

        binding.navMenu?.groupsRecView?.layoutManager = LinearLayoutManager(this)
        binding.navMenu?.groupsRecView?.adapter = groupsAdapter

        groupsViewModel.getAllGroups().subscribe {
            groups.clear()
            groups.addAll(it)
            groupsAdapter.notifyDataSetChanged()
        }

        // See all debts
        binding.navMenu?.allDebts?.setOnClickListener {
            groupsViewModel.currentGroupId = 0
            friendsViewModel.refreshFriends(true)
        }

        toggle.syncState()

        // add friend dialog
        val addFriendDialog = AddFriendDialog(this, binding)
        binding.navMenu?.btnAddFriend?.setOnClickListener { addFriendDialog.show() }

        // add group dialog
        val createGroupDialog = CreateGroupDialog(this, binding)
        binding.navMenu?.btnCreateGroup?.setOnClickListener { createGroupDialog.show() }

        // Set up tabs
        fragmentsAdapter = FragmentsAdapter(supportFragmentManager, applicationContext)
        binding.pager.adapter = fragmentsAdapter
        binding.tabs.setupWithViewPager(binding.pager)

        // FAB
        binding.fab.setOnClickListener { _ ->
            val intent = Intent(this, PaymentActivity::class.java)
            startActivityForResult(intent, debtRequestcode)
        }
    }

    override fun onBackPressed() {
        val drawer = binding.drawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }
}
