package com.example.vitaura.ui.mail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentMessageBinding
import com.example.vitaura.extensions.viewBinding

class MessageFragment: Fragment(R.layout.fragment_message) {
    val layout by viewBinding(FragmentMessageBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabs()
    }

    private fun initTabs(){

        layout.messageTab.addTab(layout.messageTab.newTab().setText("Отзыв"))
        layout.messageTab.addTab(layout.messageTab.newTab().setText("Запись на приём"))
    }

}