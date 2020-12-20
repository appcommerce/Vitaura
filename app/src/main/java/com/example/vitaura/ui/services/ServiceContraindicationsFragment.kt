package com.example.vitaura.ui.services

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentContraindicationsBinding
import com.example.vitaura.extensions.HTMLNormalizer
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.viewmodel.ServiceViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ServiceContraindicationsFragment: Fragment(R.layout.fragment_contraindications) {
    private val layout by viewBinding(FragmentContraindicationsBinding::bind)
    private val serviceViewModel by sharedViewModel<ServiceViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layout.contraindications.text = if (serviceViewModel.service
                ?.fieldMobileDescription3.isNullOrEmpty()) "Информация временно отсутствует"
        else HTMLNormalizer.normalise(serviceViewModel.service
            ?.fieldMobileDescription3.orEmpty())
    }
}