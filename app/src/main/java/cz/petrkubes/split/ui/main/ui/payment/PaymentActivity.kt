package cz.petrkubes.split.ui.main.ui.payment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.ActivityDebtBinding
import cz.petrkubes.split.ui.main.core.database.model.User
import cz.petrkubes.split.ui.main.ui.App
import cz.petrkubes.split.ui.main.ui.ViewModelFactory
import cz.petrkubes.split.ui.main.ui.adapters.ListAdapter
import cz.petrkubes.split.ui.main.ui.viewModels.FriendsViewModel
import cz.petrkubes.split.ui.main.ui.viewModels.GroupsViewModel
import cz.petrkubes.split.ui.main.ui.viewModels.PaymentsViewModel


class PaymentActivity : AppCompatActivity() {

    lateinit var binding: ActivityDebtBinding
    var payingUser: User? = null

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

        val friends: MutableList<User> = mutableListOf(User("asdf"), User("bsdf"), User("csdf"))
        val adapter = ListAdapter(this, friends, R.layout.item_friend_simple, { user, constraint -> user.name.contains(constraint) })

        binding.actvWho.setAdapter(adapter)
        binding.actvWho.threshold = 0

        binding.floatingActionButton.setOnClickListener {

        }
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return super.onNavigateUp()
    }

    fun validateAndSaveTheDebt() {

    }


}
