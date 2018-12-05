package com.example.bonnaudgastard.projet_bonnaud_gastard;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebPage extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}