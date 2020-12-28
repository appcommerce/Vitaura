package com.appcommerce.vitaura.ui.about

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.FragmentAboutInfoBinding
import com.appcommerce.vitaura.extensions.HTMLNormalizer
import com.appcommerce.vitaura.extensions.viewBinding
import com.appcommerce.vitaura.pojo.Page
import com.appcommerce.vitaura.pojo.Results
import com.appcommerce.vitaura.ui.base.BaseFragment
import com.appcommerce.vitaura.viewmodel.AboutViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AboutInfoFragment: BaseFragment(R.layout.fragment_about_info) {
    private val layout by viewBinding(FragmentAboutInfoBinding::bind)
    private val observerPage = Observer<Results<Page>>{ handlePage(it) }
    private val aboutViewModel by viewModel<AboutViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        aboutViewModel.getPage("8d9a91a5-58e7-4700-9e88-f42d848b9498", "info").observe(viewLifecycleOwner, observerPage)
    }

    private fun handlePage(results: Results<Page>) {
        when(results){
            is Results.Success ->{
                hideLoading()
                results.data?.let {
                    layout.infoTitle.text = it.title
                    layout.infoDescription.text = HTMLNormalizer.normalise(it.text.orEmpty())
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