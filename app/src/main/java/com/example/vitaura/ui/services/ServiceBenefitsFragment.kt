package com.example.vitaura.ui.services

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentBenefitsBinding
import com.example.vitaura.extensions.HTMLNormalizer
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.viewmodel.ServiceViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ServiceBenefitsFragment: Fragment(R.layout.fragment_benefits) {
    private val layout by viewBinding(FragmentBenefitsBinding::bind)
    private val serviceViewModel by sharedViewModel<ServiceViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layout.benefits.text = if (serviceViewModel.service
                ?.fieldMobileDescription2.isNullOrEmpty()) "Информация временно отсутствует"
        else HTMLNormalizer.normalise(serviceViewModel.service
            ?.fieldMobileDescription2.orEmpty())
    }
}