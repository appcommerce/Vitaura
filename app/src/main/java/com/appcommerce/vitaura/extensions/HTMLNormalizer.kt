package com.appcommerce.vitaura.extensions

import android.text.Spanned
import androidx.core.text.HtmlCompat
import org.jsoup.Jsoup
import org.jsoup.safety.Whitelist

object HTMLNormalizer {
    private val whiteList: Whitelist = Whitelist()
        .addTags("p", "br", "strong", "ul", "li", "h2")

    fun normalise(html: String): Spanned{
        val doc = Jsoup.clean(Jsoup.parse(html).html(), whiteList)
        return HtmlCompat.fromHtml(doc, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    fun normaliseWithoutMoreSpace(html: String): String{
        val doc = Jsoup.clean(Jsoup.parse(html).html(), whiteList)
        return Jsoup.parse(doc).text()
    }
}