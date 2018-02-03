package cz.petrkubes.split.ui.main.ui.payment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cz.petrkubes.split.R
import cz.petrkubes.split.databinding.ActivityDebtBinding
import cz.petrkubes.split.ui.main.core.database.model.Payment

class PaymentActivity : AppCompatActivity() {

    lateinit var viewModel: PaymentActivityViewModel
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
        viewModel = ViewModelProviders.of(this).get(PaymentActivityViewModel::class.java)

        binding.floatingActionButton.setOnClickListener {

        }
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return super.onNavigateUp()
    }

    fun validateAndSaveTheDebt() {
        val debt: Payment = Payment(0, 0)
        debt.note = binding.textNote.text.toString()
        viewModel.savePayment(debt)
    }


}
