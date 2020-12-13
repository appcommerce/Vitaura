package com.example.vitaura.extensions

import co.nedim.maildroidx.MaildroidX
import co.nedim.maildroidx.MaildroidXType

object SMTPClient {
    fun sendMessage(text: String, theme: String, callback: MaildroidX.onCompleteCallback) = MaildroidX.Builder()
                .smtp(Constants.SMTP_SERVER)
                .smtpUsername(Constants.SMTP_LOGIN)
                .smtpPassword(Constants.SMTP_PASSWORD)
                .port(Constants.SMTP_PORT)
                .type(MaildroidXType.HTML)
                .to(Constants.EMAIL_RECEIVER)
                .from(Constants.EMAIL_SENDER)
                .subject(theme)
                .body(text)
                .onCompleteCallback(callback)
                .mail()
}