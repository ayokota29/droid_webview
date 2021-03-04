package com.example.webview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ボタンを設定
        val openButton = findViewById<Button>(R.id.openButton) as Button

        // EditText の設定
        val requestUrl = findViewById<EditText>(R.id.requestUrl) as EditText

        // webview を開く
        openButton.setOnClickListener {
            setContentView(R.layout.activity_webview)
            val mockWebView: WebView = findViewById(R.id.mock)

            mockWebView.setWebViewClient(object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    return false
                }
            })

            if(!requestUrl.text.isNullOrBlank()){
                // 取得したテキストを TextView に張り付ける
                print(requestUrl.text.toString())
                mockWebView.loadUrl(requestUrl.text.toString())
            } else {
                // google.com
                mockWebView.loadUrl("https://www.google.com/")
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        val mockWebView: WebView = findViewById(R.id.mock)
        mockWebView.webViewClient = WebViewClient()

        if (keyCode == KeyEvent.KEYCODE_BACK && mockWebView.canGoBack()) {
            mockWebView.goBack()
            return true
        }

        // 戻り先がなければsetContentView(R.layout.activity_main)にしたい

        return super.onKeyDown(keyCode, event)
    }
}
