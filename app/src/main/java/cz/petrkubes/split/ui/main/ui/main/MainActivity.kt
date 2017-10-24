package cz.petrkubes.split.ui.main.ui.main

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.ActivityMainBinding
import cz.petrkubes.split.ui.main.repositories.UserRepository
import cz.petrkubes.split.ui.main.ui.App
import cz.petrkubes.split.ui.main.ui.ViewModelFactory
import cz.petrkubes.split.ui.main.ui.friends.AddFriendDialog
import cz.petrkubes.split.ui.main.ui.groups.CreateGroupDialog
import cz.petrkubes.split.ui.main.ui.payment.PaymentActivity
import cz.petrkubes.split.ui.main.util.debtRequestcode
import javax.inject.Inject

class MainActivity : AppCompatActivity(), LifecycleRegistryOwner {

    var lifecycleRegistry = LifecycleRegistry(this)
    lateinit var fragmentsAdapter: FragmentsAdapter

    @Inject
    lateinit var userRepository: UserRepository;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel: MainActivityViewModel = ViewModelProviders.of(this, ViewModelFactory(application as App)).get(MainActivityViewModel::class.java)

        // Set up action bar
        setSupportActionBar(binding.toolbar)

        // Set up drawer
        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout,binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // add friend dialog
        val addFriendDialog = AddFriendDialog(this, binding)
        binding.navMenu.btnAddFriend.setOnClickListener { addFriendDialog.show() }

        // add group dialog
        val createGroupDialog = CreateGroupDialog(this, binding)
        binding.navMenu.btnCreateGroup.setOnClickListener { createGroupDialog.show() }

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
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
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
