package com.appcommerce.vitaura.ui.base

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment(layoutForm: Int): Fragment(layoutForm) {
    abstract fun showLoading()
    abstract fun hideLoading()
    fun handleError(error: Throwable?){
        error?.message?.let {
            Snackbar.make(requireView().rootView, it, Snackbar.LENGTH_LONG).show()
        }
    }
}