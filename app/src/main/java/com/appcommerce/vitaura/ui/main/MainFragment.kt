package com.appcommerce.vitaura.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.pojo.Results
import com.appcommerce.vitaura.databinding.FragmentMainBinding
import com.appcommerce.vitaura.extensions.Router
import com.appcommerce.vitaura.extensions.viewBinding
import com.appcommerce.vitaura.pojo.Slider
import com.appcommerce.vitaura.ui.base.BaseFragment
import com.appcommerce.vitaura.ui.feedback.FeedbackFragment
import com.appcommerce.vitaura.ui.info.InfoFragment
import com.appcommerce.vitaura.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayout
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment: BaseFragment(R.layout.fragment_main), TabLayout.OnTabSelectedListener {
    private val mainViewModel: MainViewModel by viewModel()
    private val observeSlides = Observer<Results<List<Slider>>> { handleSlides(it) }
    private val layout by viewBinding(FragmentMainBinding::bind)
    private var sliderAdapter: MainSliderAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initSlider()
        initTabs()
        mainViewModel.getSlides().observe(viewLifecycleOwner, observeSlides)
    }

    private fun initSlider(){
        sliderAdapter = MainSliderAdapter()
        sliderAdapter?.let {
            layout.mainSlider.setSliderAdapter(it)
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity)
            .supportActionBar?.apply {
                title = "Главная"
            }
    }

    private fun initTabs(){
        Router.routeTabFragment(this, InfoFragment(), R.id.tab_container)
        layout.mainTab.addTab(layout.mainTab.newTab().setText("Информация"))
        layout.mainTab.addTab(layout.mainTab.newTab().setText("Отзывы"))
        layout.mainTab.addOnTabSelectedListener(this)
    }

    private fun handleSlides(result: Results<List<Slider>>){
        when(result){
            is Results.Success ->{
                hideLoading()
                result.data?.let {
                    showSlides(it)
                }
            }
            is Results.Loading ->{
                showLoading()
            }
            is Results.Error ->{
                hideLoading()
                handleError(result.throwable)
            }
        }
    }

    override fun showLoading(){

    }

    override fun hideLoading(){

    }

    private fun showSlides(result: List<Slider>) {
        sliderAdapter?.addSlides(result)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when(tab?.position){
            0 -> Router.routeTabFragment(this, InfoFragment(), R.id.tab_container)
            1 -> Router.routeTabFragment(this, FeedbackFragment(), R.id.tab_container)
        }
    }
    override fun onTabUnselected(tab: TabLayout.Tab?) {}
    override fun onTabReselected(tab: TabLayout.Tab?) {}

    override fun onDestroyView() {
        layout.mainTab.removeOnTabSelectedListener(this)
        super.onDestroyView()
    }
}