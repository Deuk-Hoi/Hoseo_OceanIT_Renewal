package com.deuksoft.hoseooceanit2.ui.water

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.deuksoft.hoseooceanit2.HTTPManager.Tools
import com.deuksoft.hoseooceanit2.databinding.FragmentLogBinding

class LogFragment:Fragment() {
    private var _logFragment : FragmentLogBinding? = null
    private val logFragment get() = _logFragment!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _logFragment = FragmentLogBinding.inflate(inflater, container, false)
        var webSettings = logFragment.logWeb.settings
        webSettings.apply {
            javaScriptEnabled = true
            setSupportMultipleWindows(true)
            javaScriptCanOpenWindowsAutomatically = true
            loadWithOverviewMode = true
            useWideViewPort = true
            setSupportZoom(true)
            builtInZoomControls = true
            cacheMode = WebSettings.LOAD_NO_CACHE
            domStorageEnabled = true
        }
        logFragment.logWeb.loadUrl("${Tools().DUMCN_BaseURL}/deviceLog.do")

        logFragment.logWeb.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view!!.loadUrl(url!!)
                return true
            }
        }
        return logFragment.root
    }
    override fun onDestroy() {
        super.onDestroy()
        _logFragment = null
    }
}