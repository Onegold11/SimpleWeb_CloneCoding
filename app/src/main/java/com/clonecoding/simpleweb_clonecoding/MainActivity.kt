package com.clonecoding.simpleweb_clonecoding

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.URLUtil
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.widget.ContentLoadingProgressBar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {

    /**
     * Home button
     */
    private val homeButton: ImageButton by lazy {
        findViewById(R.id.homeButton)
    }

    /**
     * Back button
     */
    private val backButton: ImageButton by lazy {
        findViewById(R.id.backButton)
    }

    /**
     * Forward button
     */
    private val forwardButton: ImageButton by lazy {
        findViewById(R.id.forwardButton)
    }

    /**
     * Address edit
     */
    private val addressEdit: EditText by lazy {
        findViewById(R.id.addressEdit)
    }

    /**
     * Refresh layout
     */
    private val refreshLayout: SwipeRefreshLayout by lazy {
        findViewById(R.id.refreshLayout)
    }

    /**
     * Progress bar
     */
    private val progressBar: ContentLoadingProgressBar by lazy {
        findViewById(R.id.progressBar)
    }

    /**
     * Web view
     */
    private val webView: WebView by lazy {
        findViewById(R.id.webView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initViews()
        this.bindViews()
    }

    /**
     * Init view
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun initViews() {

        this.webView.apply {

            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            settings.javaScriptEnabled = true
            loadUrl(HOME_URL)
        }
    }

    /**
     * Bind view
     */
    private fun bindViews() {

        this.addressEdit.setOnEditorActionListener { v, actionId, event ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {

                val loadingUrl = v.text.toString()
                if(URLUtil.isNetworkUrl(loadingUrl)) {
                    this.webView.loadUrl(loadingUrl)
                } else {
                    this.webView.loadUrl("http://$loadingUrl")
                }
            }

            return@setOnEditorActionListener false
        }

        this.homeButton.setOnClickListener {

            this.webView.loadUrl(HOME_URL)
        }

        this.backButton.setOnClickListener {
            this.webView.goBack()
        }

        this.forwardButton.setOnClickListener {
            this.webView.goForward()
        }

        this.refreshLayout.setOnRefreshListener {

            this.webView.reload()
        }
    }

    inner class WebViewClient: android.webkit.WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            progressBar.show()
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            refreshLayout.isRefreshing = false
            progressBar.hide()
            backButton.isEnabled = webView.canGoBack()
            forwardButton.isEnabled = webView.canGoForward()
            addressEdit.setText(url)
        }
    }

    inner class WebChromeClient: android.webkit.WebChromeClient() {

        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)

            progressBar.progress = newProgress
        }
    }

    override fun onBackPressed() {

        if (this.webView.canGoBack()) {
            this.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private const val HOME_URL = "http://www.google.com"
    }
}