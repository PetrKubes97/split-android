package cz.petrkubes.split.ui.main.ui.debt

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.View
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.ActivityDebtBinding
import cz.petrkubes.split.ui.main.core.database.model.Currency
import cz.petrkubes.split.ui.main.core.database.model.Debt
import cz.petrkubes.split.ui.main.core.database.model.Group
import cz.petrkubes.split.ui.main.core.database.model.User
import cz.petrkubes.split.ui.main.ui.App
import cz.petrkubes.split.ui.main.ui.ViewModelFactory
import cz.petrkubes.split.ui.main.ui.adapters.ListAdapter
import cz.petrkubes.split.ui.main.ui.viewModels.DebtsViewModel
import cz.petrkubes.split.ui.main.ui.viewModels.FriendsViewModel
import cz.petrkubes.split.ui.main.ui.viewModels.GroupsViewModel


class DebtActivity : AppCompatActivity() {

    lateinit var binding: ActivityDebtBinding

    var paidBy: User? = null
    var paidForUser: User? = null
    var paidForGroup: Group? = null

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
        val debtsViewModel: DebtsViewModel = ViewModelProviders.of(this, ViewModelFactory(application as App)).get(DebtsViewModel::class.java)

        val friends: MutableList<User> = mutableListOf()
        val adapterFriends = ListAdapter(this, friends, R.layout.item_simple, { user, constraint -> user.name.contains(constraint) })
        val groups: MutableList<Group> = mutableListOf()
        val adapterGroups = ListAdapter(this, groups, R.layout.item_simple, { group, constraint -> group.name.contains(constraint) })

        friendsViewModel.getAllFriends().subscribe {
            friends.clear()
            friends.addAll(it)
            adapterFriends.notifyDataSetChanged()
        }

        groupsViewModel.getAllGroups().subscribe {
            groups.clear()
            groups.addAll(it)
            adapterGroups.notifyDataSetChanged()
        }


        binding.actvWho.setAdapter(adapterFriends)
        binding.actvWho.threshold = 0
        binding.actvWho.setOnItemClickListener { _, _, position, _ ->
            paidBy = adapterFriends.getItem(position)
        }

        // Set up debtor
        binding.rdioFriend.isChecked = groupsViewModel.currentGroupId == 0
        // Show group spinner or a friend
        fun setUpWhom() {
            if (binding.rdioFriend.isChecked) {
                binding.actvForWhom.setAdapter(adapterFriends)
            } else {
                binding.actvForWhom.setAdapter(adapterGroups)
            }
        }

        binding.rdioGrpForWhom.setOnCheckedChangeListener { _, _ ->
            setUpWhom()
        }
        setUpWhom()

        // Set up for whom auto complete text view
        binding.actvForWhom.threshold = 0
        binding.actvForWhom.setOnItemClickListener { _, _, position, _ ->
            // TODO
            binding.actvForWhom.adapter.getItem(position)
        }

        // Set up what
        binding.rdioMoney.isChecked = true
        binding.rdioGrpWhat.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.rdioMoney) {
                binding.etWhat.inputType = InputType.TYPE_CLASS_NUMBER
                binding.spnCurrency.visibility = View.VISIBLE
            } else {
                binding.etWhat.inputType = InputType.TYPE_CLASS_TEXT
                binding.spnCurrency.visibility = View.GONE
            }
        }

        val currencies: MutableList<Currency> = mutableListOf(Currency("CZK", 25), Currency("EUR", 25), Currency("USD", 25))
        val currenciesAdapter = ListAdapter(this, currencies, R.layout.item_simple)

        binding.spnCurrency.adapter = currenciesAdapter
        // TODO get currencies from database

        binding.fabButton.setOnClickListener {
            val debt = Debt()

            debt.paidBy = if (paidBy == null) User(binding.actvWho.text.toString()) else paidBy
            debt.paidFor = if (paidForUser == null) User(binding.actvForWhom.text.toString()) else paidForUser

            debt.thingName = if (binding.rdioThing.isChecked && !binding.etWhat.text.toString().isEmpty()) {
                binding.etWhat.text.toString()
            } else {
                null
            }

            debt.amount = if (binding.rdioMoney.isChecked && !binding.etWhat.text.toString().isEmpty()) {
                Integer.valueOf(binding.etWhat.text.toString())
            } else {
                null
            }

            debtsViewModel.insertDebt(debt)
            finish()
        }
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return super.onNavigateUp()
    }

    fun validateAndSaveTheDebt() {

    }


}
