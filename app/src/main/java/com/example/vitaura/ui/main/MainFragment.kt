package com.example.vitaura.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.vitaura.R
import com.example.vitaura.pojo.Results
import com.example.vitaura.databinding.FragmentMainBinding
import com.example.vitaura.extensions.ViewPagerTabAdapter
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.pojo.Slider
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.ui.feedback.FeedbackFragment
import com.example.vitaura.ui.info.InfoFragment
import com.example.vitaura.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment: BaseFragment(R.layout.fragment_main) {
    private val mainViewModel: MainViewModel by viewModel()
    private val observeSlides = Observer<Results<List<Slider>>> { handleSlides(it) }
    private val layout by viewBinding(FragmentMainBinding::bind)
    private var sliderAdapter: MainSliderAdapter? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initSlider()
        initViewPager()
        initTabs()
        mainViewModel.getSlides().observe(this, observeSlides)
    }

    private fun initSlider(){
        sliderAdapter = MainSliderAdapter()
        sliderAdapter?.let {
            layout.mainSlider.setSliderAdapter(it)
        }
    }

    private fun initTabs(){
        layout.viewPager.requestDisallowInterceptTouchEvent(true)
        TabLayoutMediator(layout.mainTab, layout.viewPager) { tab, position ->
            when(position){
                0 -> tab.text = "Информация"
                1 -> tab.text = "Отзывы"
            }
        }.attach()
    }

    private fun initViewPager(){
        layout.viewPager.offscreenPageLimit = 1
        val adapter = ViewPagerTabAdapter(this)
        adapter.addFragment(InfoFragment())
        adapter.addFragment(FeedbackFragment())
        layout.viewPager.adapter = adapter
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
}