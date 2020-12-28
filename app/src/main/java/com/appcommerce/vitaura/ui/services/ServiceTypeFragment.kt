package com.appcommerce.vitaura.ui.services

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.FragmentServiceTypeBinding
import com.appcommerce.vitaura.extensions.Router
import com.appcommerce.vitaura.extensions.viewBinding
import com.appcommerce.vitaura.pojo.Results
import com.appcommerce.vitaura.pojo.ServiceType
import com.appcommerce.vitaura.ui.base.BaseFragment
import com.appcommerce.vitaura.viewmodel.ServiceViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ServiceTypeFragment: BaseFragment(R.layout.fragment_service_type), OnServiceTypeClickListener {
    private val serviceViewModel by sharedViewModel<ServiceViewModel>()
    private val layout by viewBinding(FragmentServiceTypeBinding::bind)
    private var serviceTypeAdapter: ServiceTypeAdapter? = null
    private val serviceObserver = Observer<Results<List<ServiceType>>>{ handleService(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initServiceList()
        serviceViewModel.getServiceTypes().observe(viewLifecycleOwner, serviceObserver)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity)
            .supportActionBar?.apply {
                title = "Услуги"
            }
    }

    private fun initServiceList(){
        serviceTypeAdapter = ServiceTypeAdapter()
        serviceTypeAdapter?.setServiceTypeClickListener(this)
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

    override fun getServiceByType(aliasType: String, imgType: Int, nameType: String) {
        serviceViewModel.apply {
            serviceTypeAlias = aliasType
            serviceTypeImg = imgType
            serviceTypeName = nameType
        }
        Router.routeFragment(requireActivity(), ServiceSubTypeFragment(), R.id.main_container)
    }

}