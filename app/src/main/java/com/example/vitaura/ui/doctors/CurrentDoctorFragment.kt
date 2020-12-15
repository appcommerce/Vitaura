package com.example.vitaura.ui.doctors

import android.os.Bundle
import android.view.View
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentCurrentDoctorBinding
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.ui.base.BaseFragment

class CurrentDoctorFragment: BaseFragment(R.layout.fragment_current_doctor) {
    val layout by viewBinding(FragmentCurrentDoctorBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

}