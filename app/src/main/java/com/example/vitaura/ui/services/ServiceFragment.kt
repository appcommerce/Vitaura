package com.example.vitaura.ui.services

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentServiceBinding
import com.example.vitaura.extensions.Router
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.pojo.NodeDoctor
import com.example.vitaura.pojo.Results
import com.example.vitaura.pojo.Service
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.ui.doctors.CurrentDoctorFragment
import com.example.vitaura.ui.doctors.DoctorsAdapter
import com.example.vitaura.ui.doctors.OnDoctorClickListener
import com.example.vitaura.ui.mail.CallbackFragment
import com.example.vitaura.viewmodel.DoctorsViewModel
import com.example.vitaura.viewmodel.ServiceViewModel
import com.google.android.material.tabs.TabLayout
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ServiceFragment: BaseFragment(R.layout.fragment_service), TabLayout.OnTabSelectedListener,
    OnDoctorClickListener {
    private val layout by viewBinding(FragmentServiceBinding::bind)
    private val serviceViewModel by sharedViewModel<ServiceViewModel>()
    private val docViewModel by sharedViewModel<DoctorsViewModel>()
    private var doctorsAdapter: DoctorsAdapter? = null
    private val doctorsObserver = Observer<Results<List<NodeDoctor>>>{ handleDoctors(it) }
    private val serviceObserver = Observer<Results<Service>>{ handleService(it) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabs()
        initDoctorsList()
        serviceViewModel.serviceTypeImg?.let {
            layout.serviceAva.setImageBitmap(BitmapFactory.decodeResource(resources, it))
        }
        layout.serviceType.text = serviceViewModel.serviceTypeName
        layout.incFlower.logInFlowerBtn.setOnClickListener {
            Router.routeFragment(requireActivity(), CallbackFragment(), R.id.main_container)
        }
        serviceViewModel.serviceTid?.let {
            serviceViewModel.getServiceById(it).observe(viewLifecycleOwner, serviceObserver)
            docViewModel.getDoctorsByServiceId(it).observe(viewLifecycleOwner, doctorsObserver)
        }
    }

    private fun initTabs(){
        Router.routeTabFragment(this, ServiceEffectiveFragment(), R.id.service_container)
        layout.serviceTab.addTab(layout.serviceTab.newTab().setText("Эффективность"))
        layout.serviceTab.addTab(layout.serviceTab.newTab().setText("Преимущества"))
        layout.serviceTab.addTab(layout.serviceTab.newTab().setText("Противопоказания"))
        layout.serviceTab.addOnTabSelectedListener(this)
    }

    private fun initDoctorsList(){
        doctorsAdapter = DoctorsAdapter()
        doctorsAdapter?.setDoctorListener(this)
        layout.rvDoctors.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = doctorsAdapter
        }
    }

    private fun handleService(result: Results<Service>){
        when(result){
            is Results.Success ->{
                hideLoading()
                result.data?.let {
                    layout.serviceName.text = it.name
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

    private fun handleDoctors(result: Results<List<NodeDoctor>>){
        when(result){
            is Results.Success ->{
                hideLoading()
                result.data?.let {
                    showDoctors(it)
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

    private fun showDoctors(doctors: List<NodeDoctor>){
        doctorsAdapter?.setDoctors(doctors, doctors[0].photoUrl ?: listOf())
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when(tab?.position){
            0 -> Router.routeTabFragment(this, ServiceEffectiveFragment(), R.id.service_container)
            1 -> Router.routeTabFragment(this, ServiceBenefitsFragment(), R.id.service_container)
            2 -> Router.routeTabFragment(this, ServiceContraindicationsFragment(), R.id.service_container)
        }
    }
    override fun onTabUnselected(tab: TabLayout.Tab?) {}
    override fun onTabReselected(tab: TabLayout.Tab?) {}

    override fun getDoctorById(id: String?) {
        id?.let {
            docViewModel.doctorId = id
            Router.routeFragment(requireActivity(), CurrentDoctorFragment(), R.id.main_container)
        }
    }

    override fun getCallback() {
        Router.routeFragment(requireActivity(), CallbackFragment(), R.id.main_container)
    }

    override fun onDestroyView() {
        layout.serviceTab.removeOnTabSelectedListener(this)
        super.onDestroyView()
    }
}