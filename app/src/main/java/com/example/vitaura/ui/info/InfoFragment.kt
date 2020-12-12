package com.example.vitaura.ui.info

import android.os.Bundle
import android.view.View
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentAboutBinding
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.ui.base.BaseFragment

class InfoFragment: BaseFragment(R.layout.fragment_about) {
    val layout by viewBinding(FragmentAboutBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}