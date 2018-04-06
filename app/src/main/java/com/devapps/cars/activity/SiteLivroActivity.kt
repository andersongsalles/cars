package com.devapps.cars.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.devapps.cars.R
import com.devapps.cars.activity.dialogs.AboutDialog
import com.devapps.cars.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_site_livro.*
import kotlinx.android.synthetic.main.activity_site_livro.view.*

class SiteLivroActivity : BaseActivity() {
    private val URL_SOBRE = "http://arearestrita.semax.com.br:8086"
    override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)
        setContentView(R.layout.activity_site_livro)

        val actionBar = setupToolbar(R.id.toolbar)
        actionBar.setDisplayHomeAsUpEnabled(true)
        // WebView
        setWebViewClient(webview)
        webview.loadUrl(URL_SOBRE)

        // Swipe to Refresh
        swipeToRefresh.setOnRefreshListener {
            webview.reload()
        }
        // Cores da animação
        swipeToRefresh.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3)
    }

    private fun setWebViewClient(webview: WebView) {
        webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                // Liga o progress
                progress.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView, url: String) {
                // Desliga o progress
                progress.visibility = View.INVISIBLE
                // Termina a animação do Swipe to Refresh
                swipeToRefresh.isRefreshing = false
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url.toString()
                if (url.endsWith("sobre.htm")) {
                    // Alerta customizado
                    AboutDialog.showAbout(supportFragmentManager)
                    // Retorna true para informar que interceptamos o evento
                    return true
                }
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
    }
}

