package com.example.vitaura.ui.services

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentBenefitsBinding
import com.example.vitaura.extensions.HTMLNormalizer
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.pojo.Results
import com.example.vitaura.pojo.Service
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.viewmodel.ServiceViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ServiceBenefitsFragment: BaseFragment(R.layout.fragment_benefits) {
    private val layout by viewBinding(FragmentBenefitsBinding::bind)
    private val serviceViewModel by sharedViewModel<ServiceViewModel>()
    private val serviceObserver = Observer<Results<Service>>{ handleService(it) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        serviceViewModel.serviceTid?.let {
            serviceViewModel.getServiceById(it).observe(viewLifecycleOwner, serviceObserver)
        }
    }

    private fun handleService(result: Results<Service>){
        when(result){
            is Results.Success ->{
                hideLoading()
                result.data?.let {
                    layout.benefits.text = if (it.benefits.isNullOrEmpty()) "Информация временно отсутствует"
                    else HTMLNormalizer.normalise(it.benefits.orEmpty())
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

    override fun showLoading() {}
    override fun hideLoading() {}
}