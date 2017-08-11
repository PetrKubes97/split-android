package cz.petrkubes.split.ui.main.ui.activities.debt

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.ActivityDebtBinding
import cz.petrkubes.split.ui.main.model.data.Debt

class DebtActivity : LifecycleActivity() {

    lateinit var viewModel: DebtActivityViewModel
    lateinit var binding: ActivityDebtBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debt)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_debt)

        // Set up action bar
        setActionBar(binding.toolbar)
        actionBar.setHomeButtonEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

        // Set up view model
        viewModel = ViewModelProviders.of(this).get(DebtActivityViewModel::class.java)

        binding.floatingActionButton.setOnClickListener {

        }
    }

    fun validateAndSaveTheDebt() {
        val debt: Debt = Debt(0, binding.textNote.text.toString())
        viewModel.saveDebt(debt)
    }


}
