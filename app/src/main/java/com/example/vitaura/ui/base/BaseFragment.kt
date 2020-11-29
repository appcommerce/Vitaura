package com.example.vitaura.ui.base

import androidx.fragment.app.Fragment
import com.example.vitaura.data.Results

abstract class BaseFragment(layoutForm: Int): Fragment(layoutForm) {
    abstract fun showLoading()
    abstract fun hideLoading()
    abstract fun handleError(error: Throwable?)
}