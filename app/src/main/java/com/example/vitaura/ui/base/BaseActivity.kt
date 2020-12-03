package com.example.vitaura.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.vitaura.R
import com.example.vitaura.databinding.ActivityMainBinding
import com.example.vitaura.ui.main.MainFragment
import com.google.android.material.navigation.NavigationView

class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val viewBind by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBind.root)
        setSupportActionBar(viewBind.toolbar)
        supportActionBar?.title = ""
        val drawerToggle = ActionBarDrawerToggle(this, viewBind.drawerLayout, viewBind.toolbar, R.string.message_1, R.string.message_1)
        viewBind.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        viewBind.navigator.setNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MainFragment())
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        viewBind.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)

    }
}