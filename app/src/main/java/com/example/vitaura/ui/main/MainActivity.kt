package com.example.vitaura.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.vitaura.data.Results
import com.example.vitaura.databinding.ActivityMainBinding
import com.example.vitaura.pojo.Page
import com.example.vitaura.ui.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        mainViewModel.getPages().observe(this, {
            when(it){
                is Results.Success<*> ->{
                    @Suppress("UNCHECKED_CAST")
                    val data = it.data as List<Page>
                    Log.w(MainActivity::class.simpleName, data[0].title ?: "olol")
                }
                is Results.Loading ->{
                    println("Loading")
                }
                is Results.Error ->{
                    println(it.throwable?.message)
                }
            }
        })
    }
}