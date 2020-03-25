package com.zhouzhou.newsdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.zhou.logutils.Logger

class WebViewFragment : Fragment() {
    private val logger = Logger("WebViewFragment")
    private var webView: WebView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        logger.d("web view oncreate view")
        val view = inflater.inflate(R.layout.fragment_webview, container, false)
        webView = view.findViewById(R.id.webView)
        webView?.let {
            initWebView(it)
        }
        return view
    }

    /**
     * 由于页面简单，所以此页面只有打开，暂时没有 ViewModel
     */
    fun initWebView(webView: WebView) {
        val bundle = arguments
//        bundle.gets
        if (bundle == null) {
            NavHostFragment.findNavController(this).popBackStack()
            return
        }
        val url = bundle.getString("url")
        logger.d("web view open url:$url")

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                logger.d("load request url:${request?.url}")
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        webView.loadUrl(url)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        webView?.destroy()
        webView = null
    }

}

