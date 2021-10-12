package com.example.jv_khadijeh;

import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class OnBackPress extends AppCompatActivity {
     WebView webView ;

    public OnBackPress(WebView webView) {
        this.webView = webView;
    }

//    public OnBackPress(int contentLayoutId, WebView webView) {
//        super(contentLayoutId);
//        this.webView = webView;
//    }


    public WebView getWebView() {
        return webView;
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else {
            finish();
        }
    }
}
