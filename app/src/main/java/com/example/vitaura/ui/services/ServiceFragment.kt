package com.example.vitaura.ui.services

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentServiceBinding
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.viewmodel.ServiceViewModel
import com.google.android.material.tabs.TabLayout
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ServiceFragment: BaseFragment(R.layout.fragment_service), TabLayout.OnTabSelectedListener {
    private val layout by viewBinding(FragmentServiceBinding::bind)
    private val serviceViewModel by sharedViewModel<ServiceViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        serviceViewModel.serviceTypeImg?.let {
            layout.serviceAva.setImageBitmap(BitmapFactory.decodeResource(resources, it))
        }
        layout.serviceType.text = serviceViewModel.serviceTypeName
        layout.serviceName.text = serviceViewModel.service?.title
        initTabs()
    }

    private fun initTabs(){
        layout.serviceTab.addTab(layout.serviceTab.newTab().setText("Эффективность"))
        layout.serviceTab.addTab(layout.serviceTab.newTab().setText("Преимущества"))
        layout.serviceTab.addTab(layout.serviceTab.newTab().setText("Противопоказания"))
        layout.serviceTab.addOnTabSelectedListener(this)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when(tab?.position){
            0 ->{

            }
            1 -> {

            }
            2 -> {

            }
        }
    }
    override fun onTabUnselected(tab: TabLayout.Tab?) {}
    override fun onTabReselected(tab: TabLayout.Tab?) {}

    override fun onDestroyView() {
        layout.serviceTab.removeOnTabSelectedListener(this)
        super.onDestroyView()
    }
}