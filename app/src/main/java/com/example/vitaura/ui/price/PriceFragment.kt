package com.example.vitaura.ui.price

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentPriceBinding
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.pojo.Prices
import com.example.vitaura.pojo.Results
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.viewmodel.PriceViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PriceFragment: BaseFragment(R.layout.fragment_price) {
    private val layout by viewBinding(FragmentPriceBinding::bind)
    private val priceViewModel by viewModel<PriceViewModel>()
    private val priceObserver = Observer<Results<List<Prices>>>{ handlePrices(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        priceViewModel.getPrices().observe(viewLifecycleOwner, priceObserver)
    }

    private fun handlePrices(result: Results<List<Prices>>){
        when(result){
            is Results.Success ->{
                hideLoading()
                result.data?.let {
                    println(it)
                }
            }
            is Results.Loading ->{
                showLoading()
            }
            is Results.Error ->{
                hideLoading()
                handleError(result.throwable)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity)
            .supportActionBar?.apply {
                title = "Цены"
            }
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }
}