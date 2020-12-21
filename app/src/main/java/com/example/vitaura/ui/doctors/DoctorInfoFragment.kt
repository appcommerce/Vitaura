package com.example.vitaura.ui.doctors

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentInfoDoctorBinding
import com.example.vitaura.extensions.HTMLNormalizer
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.pojo.NodeDoctor
import com.example.vitaura.pojo.Results
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.viewmodel.DoctorsViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class DoctorInfoFragment: BaseFragment(R.layout.fragment_info_doctor) {
    private val docViewModel by sharedViewModel<DoctorsViewModel>()
    private val layout by viewBinding(FragmentInfoDoctorBinding::bind)
    private val doctorObserver = Observer<Results<NodeDoctor>> { handleInfo(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        docViewModel.doctorId?.let {
            docViewModel.getDoctor(it).observe(viewLifecycleOwner, doctorObserver)
        } ?: kotlin.run {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun handleInfo(results: Results<NodeDoctor>) {
        when(results){
            is Results.Success ->{
                hideLoading()
                results.data?.let {
                    it.information?.let {info->
                        layout.infoDoc.text = HTMLNormalizer.normalise(info)
                    }?: run {
                        layout.infoDoc.text = "Информация временно отсутствует"
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