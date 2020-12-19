package com.example.vitaura.ui.services

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentServiceSubTypeBinding
import com.example.vitaura.extensions.Router
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.pojo.Results
import com.example.vitaura.pojo.Service
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.ui.mail.CallbackFragment
import com.example.vitaura.viewmodel.ServiceViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ServiceSubTypeFragment: BaseFragment(R.layout.fragment_service_sub_type) {
    private val serviceViewModel by sharedViewModel<ServiceViewModel>()
    private val layout by viewBinding(FragmentServiceSubTypeBinding::bind)
    private var serviceAdapter: ServiceSubTypeAdapter? = null
    private val serviceObserver = Observer<Results<List<Service>>>{ handleService(it) }

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
            Router.routFragment(requireActivity(), CallbackFragment(), R.id.main_container)
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
        layout.rvSubTypes.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = serviceAdapter
        }
    }

    private fun handleService(result: Results<List<Service>>){
        when(result){
            is Results.Success ->{
                hideLoading()
                result.data?.let { sub->
                    val subtypes = sub.filter {
                        serviceViewModel.serviceTypeAlias.orEmpty() == it.type
                    }
                    serviceAdapter?.setSubTypes(subtypes)
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