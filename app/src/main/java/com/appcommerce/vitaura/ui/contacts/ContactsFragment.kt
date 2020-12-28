package com.appcommerce.vitaura.ui.contacts

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.FragmentContactsBinding
import com.appcommerce.vitaura.extensions.viewBinding

class ContactsFragment: Fragment(R.layout.fragment_contacts) {
    private val layout by viewBinding(FragmentContactsBinding::bind)

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layout.socIcon1Contacts.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/vitaura.clinic/?fref=ts")))
        }
        layout.socIcon2Contacts.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/vitaura.clinic/")))
        }
        layout.socIcon3Contacts.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCsoIyOrUiy-OTOImJWzpTNw")))
        }
        layout.map1.webViewClient = WebViewClient()
        layout.map1.settings.javaScriptEnabled = true
        layout.map2.webViewClient = WebViewClient()
        layout.map2.settings.javaScriptEnabled = true
        layout.map1.loadData("<iframe src=\"https://yandex.ru/map-widget/v1/?um=constructor%3A9009972b4270fe453b40200c06e13c147fc9b215a7fac2fe109a095def6cd4e2&amp;source=constructor\" width=\"100%\" height=\"593\" frameborder=\"0\"></iframe>", "text/html", null)
        layout.map2.loadData("<iframe src=\"https://yandex.ru/map-widget/v1/?um=constructor%3A5b0a14d7e64a5ce9cdb72ab3b312d3049908d5b491e61142894b2d9dc35e74a1&amp;source=constructor\" width=\"100%\" height=\"593\" frameborder=\"0\"></iframe>", "text/html", null)
    }
}