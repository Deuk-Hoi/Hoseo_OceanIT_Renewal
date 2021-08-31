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
import com.deuksoft.hoseooceanit2.databinding.FragmentNetworkBinding

class NetworkFragment: Fragment() {
    private var _networkFragment : FragmentNetworkBinding? = null
    private val networkFragment get() = _networkFragment!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _networkFragment = FragmentNetworkBinding.inflate(inflater, container, false)
        var webSettings = networkFragment.networkWeb.settings
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
        networkFragment.networkWeb.loadUrl("${Tools().DUMCN_BaseURL}/deviceManagement.do")

        networkFragment.networkWeb.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view!!.loadUrl(url!!)
                return true
            }
        }
        return networkFragment.root
    }
    override fun onDestroy() {
        super.onDestroy()
        _networkFragment = null
    }
}