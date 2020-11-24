package com.example.vitaura.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vitaura.R
import com.example.vitaura.data.Result
import com.example.vitaura.pojo.Slider
import com.example.vitaura.ui.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel.getSlides().observe(this, {
            when(it){
                is Result.Success<*> ->{
                    @Suppress("UNCHECKED_CAST")
                    val data = it.data as List<Slider>
                    
                }
                is Result.Loading ->{

                }
                is Result.Error ->{

                }
            }
        })
    }
}