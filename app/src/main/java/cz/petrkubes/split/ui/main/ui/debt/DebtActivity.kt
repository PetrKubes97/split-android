package cz.petrkubes.split.ui.main.ui.debt

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.AdapterView
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.ActivityDebtBinding
import cz.petrkubes.split.ui.main.core.database.model.Currency
import cz.petrkubes.split.ui.main.core.database.model.Debt
import cz.petrkubes.split.ui.main.core.database.model.Group
import cz.petrkubes.split.ui.main.core.database.model.User
import cz.petrkubes.split.ui.main.ui.App
import cz.petrkubes.split.ui.main.ui.ViewModelFactory
import cz.petrkubes.split.ui.main.ui.adapters.ListAdapter
import cz.petrkubes.split.ui.main.ui.adapters.RecyclerViewCheckboxAdapter
import cz.petrkubes.split.ui.main.ui.viewModels.DebtsViewModel
import cz.petrkubes.split.ui.main.ui.viewModels.FriendsViewModel
import cz.petrkubes.split.ui.main.ui.viewModels.GroupsViewModel
import io.reactivex.disposables.Disposable


class DebtActivity : AppCompatActivity() {

    lateinit var binding: ActivityDebtBinding

    var inGroup: Boolean = false;
    var paidBy: User? = null
    var paidForUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debt)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_debt)

        // Set up action bar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up view model
        var friendsSubscription: Disposable? = null

        val groupsViewModel: GroupsViewModel = ViewModelProviders.of(this, ViewModelFactory(application as App)).get(GroupsViewModel::class.java)
        val friendsViewModel: FriendsViewModel = ViewModelProviders.of(this, ViewModelFactory(application as App)).get(FriendsViewModel::class.java)
        val debtsViewModel: DebtsViewModel = ViewModelProviders.of(this, ViewModelFactory(application as App)).get(DebtsViewModel::class.java)

        val friends: MutableList<User> = mutableListOf()
        val adapterFriends = ListAdapter(this, friends, R.layout.item_simple)

        val groups: MutableList<Group> = mutableListOf()
        val adapterGroups = ListAdapter(this, groups, R.layout.item_simple)
        val adapterFilterableFriends = ListAdapter(this, friends, R.layout.item_simple, { user, constraint -> user.name.contains(constraint) })

        groupsViewModel.getAllGroups().subscribe {
            groups.clear()
            groups.addAll(it)
            adapterGroups.notifyDataSetChanged()
        }

        val adapterCheckboxFriendsInGroup = RecyclerViewCheckboxAdapter(friends)

        fun toggleGroup(group: Group?) {
            if (group != null) {
                binding.spnGroup.visibility =  View.VISIBLE
                binding.etChipForWhom.visibility = View.INVISIBLE
                binding.rcvGroupMembers.visibility = View.VISIBLE

                friendsSubscription?.dispose()
                friendsSubscription = friendsViewModel.getFriendsInGroup(group.id).subscribe {
                    friends.clear()
                    friends.addAll(it)
                    adapterCheckboxFriendsInGroup.notifyDataSetChanged()
                }

            } else {
                binding.spnGroup.visibility =  View.INVISIBLE
                binding.etChipForWhom.visibility = View.VISIBLE
                binding.rcvGroupMembers.visibility = View.GONE

                friendsSubscription?.dispose()
                friendsSubscription = friendsViewModel.getAllFriends().subscribe {
                    friends.clear()
                    friends.addAll(it)
                    adapterFriends.notifyDataSetChanged()
                }
            }
        }

        toggleGroup(null)

        // Set up group
        binding.spnGroup.adapter = adapterGroups
        binding.chckbGroup.setOnCheckedChangeListener{_, isChecked ->
            if (isChecked) {
                toggleGroup(binding.spnGroup.adapter.getItem(0) as Group)
            } else {
                toggleGroup(null)
            }
        }

        binding.spnGroup.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val currentGroup = adapterGroups.getItem(position)
                friendsViewModel.getFriendsInGroup(currentGroup.id).subscribe {
                    friends.clear()
                    friends.addAll(it)
                    adapterCheckboxFriendsInGroup.notifyDataSetChanged()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.rcvGroupMembers.adapter = adapterCheckboxFriendsInGroup

        // Set up chips for non group debts
        binding.etChipForWhom.setAdapter(adapterFilterableFriends)

        binding.spnWho.adapter = adapterFriends
        binding.spnWho.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                paidBy = adapterFriends.getItem(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
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

            debt.paidBy = paidBy
            debt.paidFor = paidForUser

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
