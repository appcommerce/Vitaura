package com.example.vitaura.ui.doctors

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentSpecDoctorBinding
import com.example.vitaura.extensions.HTMLNormalizer
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.pojo.NodeDoctor
import com.example.vitaura.pojo.Results
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.viewmodel.DoctorsViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class DoctorSpecFragment: BaseFragment(R.layout.fragment_spec_doctor) {
    private val docViewModel by sharedViewModel<DoctorsViewModel>()
    private val layout by viewBinding(FragmentSpecDoctorBinding::bind)
    private val doctorObserver = Observer<Results<NodeDoctor>> { handleSpec(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        docViewModel.doctorId?.let {
            docViewModel.getDoctor(it).observe(viewLifecycleOwner, doctorObserver)
        } ?: kotlin.run {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun handleSpec(results: Results<NodeDoctor>) {
        when(results){
            is Results.Success ->{
                hideLoading()
                results.data?.let {
                    it.specialization?.let {spec->
                        layout.specDoc.text = HTMLNormalizer.normalise(spec)
                    }?: run {
                        layout.specDoc.text = "Информация временно отсутствует"
                    }
                }
            }
            is Results.Loading ->{
                showLoading()
            }
            is Results.Error ->{
                hideLoading()
                handleError(results.throwable)
            }
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}