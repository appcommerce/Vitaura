package com.example.vitaura.ui.mail

import android.os.Bundle
import android.view.View
import co.nedim.maildroidx.MaildroidX
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentCallbackBinding
import com.example.vitaura.extensions.SMTPClient
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.ui.base.BaseFragment

class CallbackFragment: BaseFragment(R.layout.fragment_callback) {
    private val layout by viewBinding(FragmentCallbackBinding::bind)

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layout.incCall.callbackSend.setOnClickListener {
            showLoading()
            val callback = "${layout.incCall.nameCallback}" +
                    "\n${layout.incCall.phoneCallback}" +
                    "\n${layout.incCall.emailCallback}" +
                    "\n${layout.incCall.calendarCallback}" +
                    "\n${layout.incCall.commentCallback}"
            SMTPClient.sendMessage(callback, "Новая запись на приём", sendCallback)
        }
    }

    private val sendCallback = object : MaildroidX.onCompleteCallback{
        override val timeout: Long = 3000
        override fun onSuccess() {
            hideLoading()
            
        }
        override fun onFail(errorMessage: String) {
            hideLoading()
            handleError(RuntimeException(errorMessage))
        }
    }
}