package com.appcommerce.vitaura.ui.services

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.FragmentServiceBinding
import com.appcommerce.vitaura.extensions.Router
import com.appcommerce.vitaura.extensions.viewBinding
import com.appcommerce.vitaura.pojo.*
import com.appcommerce.vitaura.ui.base.BaseFragment
import com.appcommerce.vitaura.ui.doctors.CurrentDoctorFragment
import com.appcommerce.vitaura.ui.doctors.DoctorsAdapter
import com.appcommerce.vitaura.ui.doctors.OnDoctorClickListener
import com.appcommerce.vitaura.ui.mail.CallbackFragment
import com.appcommerce.vitaura.ui.price.FirstLevelPriceAdapter
import com.appcommerce.vitaura.viewmodel.DoctorsViewModel
import com.appcommerce.vitaura.viewmodel.PriceViewModel
import com.appcommerce.vitaura.viewmodel.ServiceViewModel
import com.google.android.material.tabs.TabLayout
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ServiceFragment: BaseFragment(R.layout.fragment_service), TabLayout.OnTabSelectedListener,
    OnDoctorClickListener {
    private val layout by viewBinding(FragmentServiceBinding::bind)
    private val serviceViewModel by sharedViewModel<ServiceViewModel>()
    private val docViewModel by sharedViewModel<DoctorsViewModel>()
    private var doctorsAdapter: DoctorsAdapter? = null
    private val doctorsObserver = Observer<Results<List<NodeDoctor>>>{ handleDoctors(it) }
    private val serviceObserver = Observer<Results<Service>>{ handleService(it) }
    private var priceAdapter: FirstLevelPriceAdapter? = null
    private val priceViewModel by viewModel<PriceViewModel>()
    private val priceObserver = Observer<Results<Pair<MutableList<Prices>, MutableList<PricesCascade>>>>{ handlePrices(it) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabs()
        initDoctorsList()
        initPriceList()
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
        serviceViewModel.servicePage?.let {
            priceViewModel.getPriceByService(it).observe(viewLifecycleOwner, priceObserver)
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

    private fun showDoctors(doctors: List<NodeDoctor>){
        doctorsAdapter?.setDoctors(doctors, doctors[0].photoUrl ?: listOf())
    }

    private fun initPriceList() {
        priceAdapter = FirstLevelPriceAdapter()
        layout.rvServicePrice.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = priceAdapter
        }
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