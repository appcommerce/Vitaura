package com.appcommerce.vitaura.ui.services

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.FragmentServiceSubTypeBinding
import com.appcommerce.vitaura.extensions.Router
import com.appcommerce.vitaura.extensions.viewBinding
import com.appcommerce.vitaura.pojo.Results
import com.appcommerce.vitaura.pojo.ServiceSubMenu
import com.appcommerce.vitaura.ui.base.BaseFragment
import com.appcommerce.vitaura.ui.mail.CallbackFragment
import com.appcommerce.vitaura.viewmodel.ServiceViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ServiceSubTypeFragment: BaseFragment(R.layout.fragment_service_sub_type), OnServiceSubTypeClickListener {
    private val serviceViewModel by sharedViewModel<ServiceViewModel>()
    private val layout by viewBinding(FragmentServiceSubTypeBinding::bind)
    private var serviceAdapter: ServiceSubTypeAdapter? = null
    private val serviceObserver = Observer<Results<List<ServiceSubMenu>>>{ handleService(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initServiceList()
        serviceViewModel.apply {
            getServices().observe(viewLifecycleOwner, serviceObserver)
            serviceTypeName?.let {
                layout.typeName.text = it
            }
            serviceTypeImg?.let {
                layout.typeImg.setImageBitmap(BitmapFactory.decodeResource(resources, it))
            }
        }
        layout.flowerBottom.logInFlowerBtn.setOnClickListener {
            Router.routeFragment(requireActivity(), CallbackFragment(), R.id.main_container)
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity)
            .supportActionBar?.apply {
                title = "Услуги"
            }
    }

    private fun initServiceList(){
        serviceAdapter = ServiceSubTypeAdapter()
        serviceAdapter?.setServiceClickListener(this)
        layout.rvSubTypes.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = serviceAdapter
        }
    }

    private fun handleService(result: Results<List<ServiceSubMenu>>){
        when(result){
            is Results.Success ->{
                hideLoading()
                result.data?.let { sub->
                    val mainSubTypes = sub.filter { serviceViewModel.serviceTypeAlias.orEmpty() == it.type }
                    serviceAdapter?.setMainSubTypes(mainSubTypes)
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

    override fun getServiceById(serviceTid: String?, page: String?) {
        serviceTid?.let {
            serviceViewModel.serviceTid = it
            serviceViewModel.servicePage = page
            Router.routeFragment(requireActivity(), ServiceFragment(), R.id.main_container)
        }
    }
}