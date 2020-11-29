package com.example.vitaura.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.vitaura.R
import com.example.vitaura.databinding.ActivityMainBinding
import com.example.vitaura.ui.main.MainFragment
import com.google.android.material.navigation.NavigationView

class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MainFragment())
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        return true
    }
}