package com.example.vitaura.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.vitaura.R
import com.example.vitaura.pojo.Results
import com.example.vitaura.databinding.FragmentMainBinding
import com.example.vitaura.extensions.Router
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.pojo.Slider
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.ui.feedback.FeedbackFragment
import com.example.vitaura.ui.info.InfoFragment
import com.example.vitaura.viewmodel.MainViewModel
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

    override fun onDestroy() {
        super.onDestroy()
        layout.mainTab.removeOnTabSelectedListener(this)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when(tab?.position){
            0 ->{
                Router.routeTabFragment(this, InfoFragment(), R.id.tab_container)
            }
            1 ->{
                Router.routeTabFragment(this, FeedbackFragment(), R.id.tab_container)
            }
        }
    }
    override fun onTabUnselected(tab: TabLayout.Tab?) {}
    override fun onTabReselected(tab: TabLayout.Tab?) {}
}