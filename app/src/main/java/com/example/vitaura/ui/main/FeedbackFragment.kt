package com.example.vitaura.ui.main

import android.os.Bundle
import android.view.View
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentFeedbackBinding
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.ui.base.BaseFragment

class FeedbackFragment: BaseFragment(R.layout.fragment_feedback) {
    val layout by viewBinding(FragmentFeedbackBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun handleError(error: Throwable?) {

    }
}