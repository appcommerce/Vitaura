package com.appcommerce.vitaura.ui.mail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import co.nedim.maildroidx.MaildroidX
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.FragmentCallbackBinding
import com.appcommerce.vitaura.extensions.Router
import com.appcommerce.vitaura.extensions.SMTPClient
import com.appcommerce.vitaura.extensions.viewBinding
import com.appcommerce.vitaura.ui.base.BaseFragment

class CallbackFragment: BaseFragment(R.layout.fragment_callback) {
    private val layout by viewBinding(FragmentCallbackBinding::bind)

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity)
                .supportActionBar
                ?.title = "Запись на приём"
        layout.incCall.callbackSend.setOnClickListener {
            showLoading()
            val callback = "${layout.incCall.nameCallback}" +
                    "\n${layout.incCall.phoneCallback}" +
                    "\n${layout.incCall.emailCallback}" +
                    "\n${layout.incCall.calendarCallback}" +
                    "\n${layout.incCall.commentCallback}"
            SMTPClient.sendMessage(callback, getString(R.string.message_28), sendCallback)
        }
    }

    private val sendCallback = object : MaildroidX.onCompleteCallback{
        override val timeout: Long = 3000
        override fun onSuccess() {
            hideLoading()
            Router.routeFragment(requireActivity(), SuccessFragment(), R.id.main_container)
        }
        override fun onFail(errorMessage: String) {
            hideLoading()
            handleError(RuntimeException(errorMessage))
        }
    }
}