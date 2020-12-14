package com.example.vitaura.ui.mail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vitaura.R

class SuccessFragment: Fragment(R.layout.fragment_success) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity)
                .supportActionBar
                ?.title = getString(R.string.message_27)
    }
}