package cz.petrkubes.split.ui.main

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import cz.petrkubes.split.R
import cz.petrkubes.split.ui.main.adapters.FragmentsAdapter

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.fab)
    lateinit var fab: FloatingActionButton

    @BindView(R.id.drawer_layout)
    lateinit var drawer: DrawerLayout

    @BindView(R.id.nav_view)
    lateinit var navigationView: NavigationView

    @BindView(R.id.pager)
    lateinit var pager: ViewPager

    @BindView(R.id.tabs)
    lateinit var tabs: TabLayout

    lateinit var fragmentsAdapter: FragmentsAdapter

    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)

        mainPresenter = MainPresenter(this)

        // Set up action button
        fab.setOnClickListener { view ->
            mainPresenter.onAddDebtButtonClick(view)
        }

        // Set up drawer
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.setDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)

        // Set up tabs
        fragmentsAdapter = FragmentsAdapter(supportFragmentManager, applicationContext)
        pager.adapter = fragmentsAdapter
        tabs.setupWithViewPager(pager)
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        mainPresenter.onNavigationItemSelected(item)
        return true
    }

    // public functions for MainPresenter -------------------------------------------------------------

    fun closeDrawer() {
        drawer.closeDrawer(GravityCompat.START)
    }

    fun changeAppColor(color: Int) {
        toolbar.setBackgroundColor(ContextCompat.getColor(this, color))
        tabs.setBackgroundColor(ContextCompat.getColor(this, color))
    }

    fun showSnackbar(view: View) {
        Snackbar.make(view, "Hey, sup?", Snackbar.LENGTH_SHORT).show()
    }

}
