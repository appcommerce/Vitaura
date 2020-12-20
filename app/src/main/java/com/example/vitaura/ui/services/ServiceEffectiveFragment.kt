package com.example.vitaura.ui.services

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentEffectiveBinding
import com.example.vitaura.extensions.HTMLNormalizer
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.viewmodel.ServiceViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ServiceEffectiveFragment: Fragment(R.layout.fragment_effective) {
    private val layout by viewBinding(FragmentEffectiveBinding::bind)
    private val serviceViewModel by sharedViewModel<ServiceViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layout.effective.text = if (serviceViewModel.service
                ?.fieldMobileDescription1.isNullOrEmpty()) "Информация временно отсутствует"
        else HTMLNormalizer.normalise(serviceViewModel.service
            ?.fieldMobileDescription1.orEmpty())
    }
}