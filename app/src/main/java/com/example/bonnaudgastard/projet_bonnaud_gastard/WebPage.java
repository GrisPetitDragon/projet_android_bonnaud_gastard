package com.example.bonnaudgastard.projet_bonnaud_gastard;

import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebPage {
    /**
     * Charge la page web
     */
    private void loadWebApp(Context context){
        WebView myWebView = new WebView(context);
        //setContentView(myWebView);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://en.wikipedia.org/wiki/Big_Buck_Bunny");
    }
}
