package com.example.vitaura.ui.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.vitaura.R
import com.example.vitaura.data.Results
import com.example.vitaura.databinding.FragmentMainBinding
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.pojo.Slider
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.ui.base.MainActivity
import com.example.vitaura.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment: BaseFragment(R.layout.fragment_main) {
    private val mainViewModel: MainViewModel by viewModel()
    private val observeSlides = Observer<Results> { handleSlides(it) }
    private val layout by viewBinding(FragmentMainBinding::bind)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainViewModel.getSlides().observe(this, observeSlides)

    }

    private fun handleSlides(result: Results){
        when(result){
            is Results.Success<*> ->{
                @Suppress("UNCHECKED_CAST")
                showSlides(result.data as List<Slider>)
            }
            is Results.Loading ->{
                showLoading()
            }
            is Results.Error ->{
                handleError(result.throwable)
            }
        }
    }

    override fun showLoading(){
        TODO("NOT YET IMPLEMENTED")
    }

    override fun hideLoading(){
        TODO("NOT YET IMPLEMENTED")
    }

    override fun handleError(error: Throwable?) {
        hideLoading()
        println(error?.message)
    }

    private fun showSlides(result: List<Slider>) {
        hideLoading()
        Log.w(MainActivity::class.simpleName, result[0].title ?: "olol")
    }
}