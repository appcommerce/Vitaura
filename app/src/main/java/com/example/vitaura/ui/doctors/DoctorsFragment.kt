package com.example.vitaura.ui.doctors

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentDoctorsBinding
import com.example.vitaura.extensions.Router
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.pojo.NodeDoctor
import com.example.vitaura.pojo.Results
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.ui.mail.CallbackFragment
import com.example.vitaura.viewmodel.DoctorsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DoctorsFragment: BaseFragment(R.layout.fragment_doctors), OnDoctorClickListener {
    private val docViewModel by viewModel<DoctorsViewModel>()
    private val layout by viewBinding(FragmentDoctorsBinding::bind)
    private var doctorsAdapter: DoctorsAdapter? = null
    private val doctorsObserver = Observer<Results<List<NodeDoctor>>>{ handleDoctors(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDoctorsList()
        docViewModel.getDoctors().observe(viewLifecycleOwner, doctorsObserver)
        layout.incFlower.logInFlowerBtn.setOnClickListener {
            Router.routFragment(requireActivity(), CallbackFragment(), R.id.main_container)
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
            Router.routFragment(requireActivity(), CurrentDoctorFragment(), R.id.main_container)
        }
    }
}