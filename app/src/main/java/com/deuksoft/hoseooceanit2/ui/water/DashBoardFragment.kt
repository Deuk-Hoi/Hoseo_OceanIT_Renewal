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
import com.deuksoft.hoseooceanit2.databinding.FragmentDashboardBinding

class DashBoardFragment: Fragment() {
    private var _dashBoardBinding : FragmentDashboardBinding? = null
    private val dashboardBinding get() = _dashBoardBinding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _dashBoardBinding = FragmentDashboardBinding.inflate(inflater, container, false)
        var webSettings = dashboardBinding.dashWeb.settings
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
        dashboardBinding.dashWeb.loadUrl("${Tools().DUMCN_BaseURL}/deviceMain.do")

        dashboardBinding.dashWeb.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view!!.loadUrl(url!!)
                return true
            }
        }
        return dashboardBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _dashBoardBinding = null
    }
}