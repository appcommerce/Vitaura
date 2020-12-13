package com.example.vitaura.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.GravityCompat
import com.example.vitaura.R
import com.example.vitaura.databinding.ActivityMainBinding
import com.example.vitaura.ui.feedback.FeedbackFragment
import com.example.vitaura.ui.mail.MessageFragment
import com.example.vitaura.ui.main.MainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    private val viewBind by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBind.root)
        setSupportActionBar(viewBind.toolbar)
        supportActionBar?.title = "Главная"
        val drawerToggle = ActionBarDrawerToggle(this,
                viewBind.drawerLayout,
                viewBind.toolbar,
                R.string.open_hamburger,
                R.string.close_hamburger)
        viewBind.drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        viewBind.navigator.setNavigationItemSelectedListener(this)
        viewBind.bottomMenuNav.setOnNavigationItemSelectedListener(this)
        viewBind.navigator
            .getHeaderView(0)
            .findViewById<AppCompatButton>(R.id.callback_drawer_btn)
            .setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, MessageFragment())
                    .addToBackStack(null)
                    .commit()
                viewBind.drawerLayout.closeDrawer(GravityCompat.START)

            }
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, MainFragment())
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        viewBind.drawerLayout.closeDrawer(GravityCompat.START)
        return when(item.itemId){
            R.id.reviews ->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, FeedbackFragment())
                    .addToBackStack(null)
                    .commit()
                true
            }
            else -> false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.back -> {
                supportFragmentManager
                        .popBackStack()
                true
            }
            else -> false
        }
    }



    override fun onBackPressed() {
        if (viewBind.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            viewBind.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            supportFragmentManager
                    .popBackStack()
        }
    }
}