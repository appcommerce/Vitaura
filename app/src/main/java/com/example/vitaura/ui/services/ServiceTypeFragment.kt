package com.example.vitaura.ui.services

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentServiceTypeBinding
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.pojo.Results
import com.example.vitaura.pojo.ServiceType
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.viewmodel.ServiceViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ServiceTypeFragment: BaseFragment(R.layout.fragment_service_type) {
    private val serviceViewModel by sharedViewModel<ServiceViewModel>()
    private val layout by viewBinding(FragmentServiceTypeBinding::bind)
    private var serviceTypeAdapter: ServiceTypeAdapter? = null
    private val serviceObserver = Observer<Results<List<ServiceType>>>{ handleService(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initServiceList()
        serviceViewModel.getServiceTypes().observe(viewLifecycleOwner, serviceObserver)
    }

    private fun initServiceList(){
        serviceTypeAdapter = ServiceTypeAdapter()
        layout.rvServices.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = serviceTypeAdapter
        }
    }

    private fun handleService(result: Results<List<ServiceType>>){
        when(result){
            is Results.Success ->{
                hideLoading()
                result.data?.let {
                    serviceTypeAdapter?.setServiceTypes(it)
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

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

}