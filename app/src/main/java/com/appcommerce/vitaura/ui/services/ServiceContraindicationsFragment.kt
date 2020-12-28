package com.appcommerce.vitaura.ui.services

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.FragmentContraindicationsBinding
import com.appcommerce.vitaura.extensions.HTMLNormalizer
import com.appcommerce.vitaura.extensions.viewBinding
import com.appcommerce.vitaura.pojo.Results
import com.appcommerce.vitaura.pojo.Service
import com.appcommerce.vitaura.ui.base.BaseFragment
import com.appcommerce.vitaura.viewmodel.ServiceViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ServiceContraindicationsFragment: BaseFragment(R.layout.fragment_contraindications) {
    private val layout by viewBinding(FragmentContraindicationsBinding::bind)
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
                    layout.contraindications.text = if (it.contraindications.isNullOrEmpty()) "Информация временно отсутствует"
                    else HTMLNormalizer.normalise(it.contraindications.orEmpty())
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