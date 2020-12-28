package com.example.vitaura.ui.about

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentAboutLicenseBinding
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.pojo.Page
import com.example.vitaura.pojo.Results
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.viewmodel.AboutViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AboutLicenseFragment: BaseFragment(R.layout.fragment_about_license) {
    private val layout by viewBinding(FragmentAboutLicenseBinding::bind)
    private val observerDocPage = Observer<Results<Page>>{ handleDocPage(it) }
    private val observerLawPage = Observer<Results<Page>>{ handleLawPage(it) }
    private val aboutViewModel by viewModel<AboutViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        aboutViewModel.getPage("11639f05-5eab-46e6-a5d6-aaf3b28d06b2", "doc").observe(viewLifecycleOwner, observerDocPage)
        aboutViewModel.getPage("c09d6fc0-57f3-4f86-bb34-df62ecf0d6b3", "law").observe(viewLifecycleOwner, observerLawPage)
    }

    private fun handleDocPage(results: Results<Page>) {
        when(results){
            is Results.Success ->{
                hideLoading()
                results.data?.let {

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

    private fun handleLawPage(results: Results<Page>){
        when(results){
            is Results.Success ->{
                hideLoading()
                results.data?.let {

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

    override fun showLoading() {}
    override fun hideLoading() {}

}