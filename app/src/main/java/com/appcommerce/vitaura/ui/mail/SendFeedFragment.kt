package com.appcommerce.vitaura.ui.mail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import co.nedim.maildroidx.MaildroidX
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.FragmentSendBinding
import com.appcommerce.vitaura.extensions.Router
import com.appcommerce.vitaura.extensions.SMTPClient
import com.appcommerce.vitaura.extensions.viewBinding
import com.appcommerce.vitaura.ui.base.BaseFragment

class SendFeedFragment: BaseFragment(R.layout.fragment_send) {
    private val layout by viewBinding(FragmentSendBinding::bind)

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity)
                .supportActionBar
                ?.title = "Оставить отзыв"
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
            Router.routeFragment(requireActivity(), SuccessFragment(), R.id.main_container)
        }
        override fun onFail(errorMessage: String) {
            hideLoading()
            handleError(RuntimeException(errorMessage))
        }
    }
}