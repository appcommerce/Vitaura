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
import com.example.vitaura.pojo.Service
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.viewmodel.ServiceViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ServicesFragment: BaseFragment(R.layout.fragment_service_type) {
    private val serviceViewModel by sharedViewModel<ServiceViewModel>()
    private val layout by viewBinding(FragmentServiceTypeBinding::bind)
    private var serviceAdapter: ServiceTypeAdapter? = null
    private val serviceObserver = Observer<Results<List<Service>>>{ handleService(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initServiceList()
        serviceViewModel.getServices().observe(viewLifecycleOwner, serviceObserver)
    }

    private fun initServiceList(){
        serviceAdapter = ServiceTypeAdapter()
        layout.rvServices.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = serviceAdapter
        }
    }

    private fun handleService(result: Results<List<Service>>){
        when(result){
            is Results.Success ->{
                hideLoading()
                result.data?.let {

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