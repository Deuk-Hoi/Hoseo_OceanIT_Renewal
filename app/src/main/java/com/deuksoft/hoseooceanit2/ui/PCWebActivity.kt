package com.deuksoft.hoseooceanit2.ui

import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.deuksoft.hoseooceanit2.HTTPManager.Tools
import com.deuksoft.hoseooceanit2.databinding.ActivityPcwebBinding

class PCWebActivity : AppCompatActivity() {
    lateinit var pcwebBinding: ActivityPcwebBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pcwebBinding = ActivityPcwebBinding.inflate(layoutInflater)
        setContentView(pcwebBinding.root)


        var webSettings = pcwebBinding.pcweb.settings
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
        pcwebBinding.pcweb.loadUrl(Tools().MAIN_URL)

        pcwebBinding.pcweb.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view!!.loadUrl(url!!)
                return true
            }
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && pcwebBinding.pcweb.canGoBack()) {
            pcwebBinding.pcweb.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}