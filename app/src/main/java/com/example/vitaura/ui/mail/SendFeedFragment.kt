package com.example.vitaura.ui.mail

import android.os.Bundle
import android.view.View
import co.nedim.maildroidx.MaildroidX
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentSendBinding
import com.example.vitaura.extensions.SMTPClient
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.ui.base.BaseFragment

class SendFeedFragment: BaseFragment(R.layout.fragment_send) {
    private val layout by viewBinding(FragmentSendBinding::bind)

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layout.incSend.feedSend.setOnClickListener {
            showLoading()
            val feed = "${layout.incSend.name}" +
                    "\n${layout.incSend.phoneNumber}" +
                    "\n${layout.incSend.feed}"
            SMTPClient.sendMessage(feed, getString(R.string.message_26), sendFeedback)
        }
    }

    private val sendFeedback = object : MaildroidX.onCompleteCallback{
        override val timeout: Long = 3000
        override fun onSuccess() {
            hideLoading()
            requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, SuccessFragment())
        }
        override fun onFail(errorMessage: String) {
            hideLoading()
            handleError(RuntimeException(errorMessage))
        }
    }
}