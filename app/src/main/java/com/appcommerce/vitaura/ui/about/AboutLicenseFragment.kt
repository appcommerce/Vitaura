package com.appcommerce.vitaura.ui.about

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.FragmentAboutLicenseBinding
import com.appcommerce.vitaura.extensions.HTMLNormalizer
import com.appcommerce.vitaura.extensions.viewBinding
import com.appcommerce.vitaura.pojo.Page
import com.appcommerce.vitaura.pojo.Results
import com.appcommerce.vitaura.ui.base.BaseFragment
import com.appcommerce.vitaura.viewmodel.AboutViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AboutLicenseFragment: BaseFragment(R.layout.fragment_about_license) {
    private val layout by viewBinding(FragmentAboutLicenseBinding::bind)
    private val observerDocPage = Observer<Results<Page>>{ handleDocPage(it) }
    private val observerLawPage = Observer<Results<Page>>{ handleLawPage(it) }
    private val aboutViewModel by viewModel<AboutViewModel>()
    private var docsAdapter: DocsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDocList()
        aboutViewModel.getPage("11639f05-5eab-46e6-a5d6-aaf3b28d06b2", "doc").observe(viewLifecycleOwner, observerDocPage)
        aboutViewModel.getPage("c09d6fc0-57f3-4f86-bb34-df62ecf0d6b3", "law").observe(viewLifecycleOwner, observerLawPage)
    }

    private fun initDocList(){
        docsAdapter = DocsAdapter()
        layout.rvDocs.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = docsAdapter
        }
    }

    private fun handleDocPage(results: Results<Page>) {
        when(results){
            is Results.Success ->{
                hideLoading()
                results.data?.let {
                    layout.licenseTitle.text = it.title.orEmpty()
                    docsAdapter?.setDocs(it.docs ?: listOf())
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
                    layout.lawTitle.text = it.title.orEmpty()
                    layout.lawDescription.text = HTMLNormalizer.normalise(it.text.orEmpty())
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