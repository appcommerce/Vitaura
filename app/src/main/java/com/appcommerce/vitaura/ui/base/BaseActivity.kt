package com.appcommerce.vitaura.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.GravityCompat
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.ActivityMainBinding
import com.appcommerce.vitaura.extensions.Router
import com.appcommerce.vitaura.ui.about.AboutFragment
import com.appcommerce.vitaura.ui.actions.ActionsFragment
import com.appcommerce.vitaura.ui.contacts.ContactsFragment
import com.appcommerce.vitaura.ui.doctors.DoctorsFragment
import com.appcommerce.vitaura.ui.feedback.FeedbackFragment
import com.appcommerce.vitaura.ui.mail.MessageFragment
import com.appcommerce.vitaura.ui.main.MainFragment
import com.appcommerce.vitaura.ui.media.MediaFragment
import com.appcommerce.vitaura.ui.price.PriceFragment
import com.appcommerce.vitaura.ui.services.ServiceTypeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    private val viewBind by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBind.root)
        setSupportActionBar(viewBind.toolbar)
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
        if (supportFragmentManager.findFragmentById(R.id.main_container) == null){
            Router.routeFragment(this, MainFragment(), R.id.main_container)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        viewBind.drawerLayout.closeDrawer(GravityCompat.START)
        return when(item.itemId){
            R.id.main ->{
                Router.routeFragment(this, MainFragment(), R.id.main_container)
                true
            }
            R.id.reviews ->{
                Router.routeFragment(this, FeedbackFragment(), R.id.main_container)
                true
            }
            R.id.doctors ->{
                Router.routeFragment(this, DoctorsFragment(), R.id.main_container)
                true
            }
            R.id.services -> {
                Router.routeFragment(this, ServiceTypeFragment(), R.id.main_container)
                true
            }
            R.id.prices -> {
                Router.routeFragment(this, PriceFragment(), R.id.main_container)
                true
            }
            R.id.media -> {
                Router.routeFragment(this, MediaFragment(), R.id.main_container)
                true
            }
            R.id.special -> {
                Router.routeFragment(this, ActionsFragment(), R.id.main_container)
                true
            }
            R.id.about -> {
                Router.routeFragment(this, AboutFragment(), R.id.main_container)
                true
            }
            R.id.contacts -> {
                Router.routeFragment(this, ContactsFragment(), R.id.main_container)
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