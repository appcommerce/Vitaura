package com.appcommerce.vitaura.ui.doctors

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.FragmentEduDoctorBinding
import com.appcommerce.vitaura.extensions.HTMLNormalizer
import com.appcommerce.vitaura.extensions.viewBinding
import com.appcommerce.vitaura.pojo.NodeDoctor
import com.appcommerce.vitaura.pojo.Results
import com.appcommerce.vitaura.ui.base.BaseFragment
import com.appcommerce.vitaura.viewmodel.DoctorsViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class DoctorEduFragment: BaseFragment(R.layout.fragment_edu_doctor) {
    private val docViewModel by sharedViewModel<DoctorsViewModel>()
    private val layout by viewBinding(FragmentEduDoctorBinding::bind)
    private val doctorObserver = Observer<Results<NodeDoctor>> { handleEdu(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        docViewModel.doctorId?.let {
            docViewModel.getDoctor(it).observe(viewLifecycleOwner, doctorObserver)
        } ?: kotlin.run {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun handleEdu(results: Results<NodeDoctor>) {
        when(results){
            is Results.Success ->{
                hideLoading()
                results.data?.let {
                    it.education?.let {edu->
                        layout.eduDoc.text = HTMLNormalizer.normalise(edu)
                    }?: run {
                        layout.eduDoc.text = "Информация временно отсутствует"
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