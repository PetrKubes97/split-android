package cz.petrkubes.split.ui.main.ui.activities.payment

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.ActivityDebtBinding
import cz.petrkubes.split.ui.main.core.data.Payment

class PaymentActivity : LifecycleActivity() {

    lateinit var viewModel: PaymentActivityViewModel
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
        viewModel = ViewModelProviders.of(this).get(PaymentActivityViewModel::class.java)

        binding.floatingActionButton.setOnClickListener {

        }
    }

    fun validateAndSaveTheDebt() {
        val debt: Payment = Payment(0, 0)
        debt.note = binding.textNote.text.toString()
        viewModel.savePayment(debt)
    }


}
