package com.appcommerce.vitaura.ui.doctors

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.FragmentDoctorsBinding
import com.appcommerce.vitaura.extensions.Router
import com.appcommerce.vitaura.extensions.viewBinding
import com.appcommerce.vitaura.pojo.NodeDoctor
import com.appcommerce.vitaura.pojo.Results
import com.appcommerce.vitaura.ui.base.BaseFragment
import com.appcommerce.vitaura.ui.mail.CallbackFragment
import com.appcommerce.vitaura.viewmodel.DoctorsViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class DoctorsFragment: BaseFragment(R.layout.fragment_doctors), OnDoctorClickListener {
    private val docViewModel by sharedViewModel<DoctorsViewModel>()
    private val layout by viewBinding(FragmentDoctorsBinding::bind)
    private var doctorsAdapter: DoctorsAdapter? = null
    private val doctorsObserver = Observer<Results<List<NodeDoctor>>>{ handleDoctors(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDoctorsList()
        docViewModel.getDoctors().observe(viewLifecycleOwner, doctorsObserver)
        layout.incFlower.logInFlowerBtn.setOnClickListener {
            Router.routeFragment(requireActivity(), CallbackFragment(), R.id.main_container)
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity)
            .supportActionBar?.apply {
                title = "Специалисты"
            }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

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

    private fun initDoctorsList(){
        doctorsAdapter = DoctorsAdapter()
        doctorsAdapter?.setDoctorListener(this)
        layout.rvDoctors.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = doctorsAdapter
        }
    }

    override fun getDoctorById(id: String?) {
        id?.let {
            docViewModel.doctorId = id
            Router.routeFragment(requireActivity(), CurrentDoctorFragment(), R.id.main_container)
        }
    }

    override fun getCallback() {
        Router.routeFragment(requireActivity(), CallbackFragment(), R.id.main_container)
    }
}