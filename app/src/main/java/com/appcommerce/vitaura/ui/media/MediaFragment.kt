package com.appcommerce.vitaura.ui.media

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.FragmentMediaBinding
import com.appcommerce.vitaura.extensions.Router
import com.appcommerce.vitaura.extensions.viewBinding
import com.appcommerce.vitaura.ui.mail.CallbackFragment
import com.google.android.material.tabs.TabLayout

class MediaFragment: Fragment(R.layout.fragment_media), TabLayout.OnTabSelectedListener {
    private val layout by viewBinding(FragmentMediaBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMediaTabs()
        layout.fuckingFlower.logInFlowerBtn.setOnClickListener {
            Router.routeFragment(requireActivity(), CallbackFragment(), R.id.main_container)
        }
    }
    private fun initMediaTabs(){
        Router.routeTabFragment(this, MediaPhotoFragment(), R.id.media_container)
        layout.tabMedia.addTab(layout.tabMedia.newTab().setText("Фото"))
        layout.tabMedia.addTab(layout.tabMedia.newTab().setText("Видео"))
        layout.tabMedia.addOnTabSelectedListener(this)
    }
    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity)
                .supportActionBar?.apply {
                    title = "Фото и видео"
                }
    }
    override fun onDestroyView() {
        layout.tabMedia.removeOnTabSelectedListener(this)
        super.onDestroyView()
    }
    override fun onTabSelected(tab: TabLayout.Tab?) {
        when(tab?.position){
            0 -> Router.routeTabFragment(this, MediaPhotoFragment(), R.id.media_container)
            1 -> Router.routeTabFragment(this, MediaVideoFragment(), R.id.media_container)
        }
    }
    override fun onTabUnselected(tab: TabLayout.Tab?) {}
    override fun onTabReselected(tab: TabLayout.Tab?) {}
}