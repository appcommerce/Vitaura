package com.appcommerce.vitaura.ui.mail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.FragmentMessageBinding
import com.appcommerce.vitaura.extensions.Router
import com.appcommerce.vitaura.extensions.viewBinding
import com.google.android.material.tabs.TabLayout

class MessageFragment: Fragment(R.layout.fragment_message), TabLayout.OnTabSelectedListener {
    val layout by viewBinding(FragmentMessageBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity)
                .supportActionBar
                ?.title = "Оставить отзыв"
        initTabs()
    }

    private fun initTabs(){
        Router.routeTabFragment(this, SendFeedFragment(), R.id.message_container)
        layout.messageTab.addTab(layout.messageTab.newTab().setText("Отзыв"))
        layout.messageTab.addTab(layout.messageTab.newTab().setText("Запись на приём"))
        layout.messageTab.addOnTabSelectedListener(this)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when(tab?.position){
            0 ->{
                Router.routeTabFragment(this, SendFeedFragment(), R.id.message_container)
            }
            1 ->{
                Router.routeTabFragment(this, CallbackFragment(), R.id.message_container)
            }
        }
    }
    override fun onTabUnselected(tab: TabLayout.Tab?) {}
    override fun onTabReselected(tab: TabLayout.Tab?) {}

    override fun onDestroy() {
        super.onDestroy()
        layout.messageTab.removeOnTabSelectedListener(this)
    }
}