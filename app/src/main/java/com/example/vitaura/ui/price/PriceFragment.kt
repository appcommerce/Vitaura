package com.example.vitaura.ui.price

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentPriceBinding
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.pojo.Prices
import com.example.vitaura.pojo.PricesCascade
import com.example.vitaura.pojo.Results
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.viewmodel.PriceViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PriceFragment: BaseFragment(R.layout.fragment_price) {
    private val layout by viewBinding(FragmentPriceBinding::bind)
    private var priceAdapter: FirstLevelPriceAdapter? = null
    private val priceViewModel by viewModel<PriceViewModel>()
    private val priceObserver = Observer<Results<Pair<MutableList<Prices>, MutableList<PricesCascade>>>>{ handlePrices(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPriceList()
        priceViewModel.getPrices().observe(viewLifecycleOwner, priceObserver)
    }

    private fun initPriceList() {
        priceAdapter = FirstLevelPriceAdapter()
        layout.rvPrice.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = priceAdapter
        }
    }

    private fun handlePrices(result: Results<Pair<MutableList<Prices>, MutableList<PricesCascade>>>){
        val resultList = mutableListOf<Any>()
        when(result){
            is Results.Success ->{
                hideLoading()
                result.data?.let {
                    resultList.clear()
                    resultList.addAll(it.first)
                    resultList.addAll(it.second)
                    priceAdapter?.setFirstLevelPrice(resultList)
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
        layout.rvPrice.visibility = View.INVISIBLE
        layout.loadPrice.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        layout.loadPrice.visibility = View.INVISIBLE
        layout.rvPrice.visibility = View.VISIBLE
    }
}