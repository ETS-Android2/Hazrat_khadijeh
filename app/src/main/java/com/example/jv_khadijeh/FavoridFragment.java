package com.example.jv_khadijeh;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.lottie.LottieAnimationView;

public class FavoridFragment extends Fragment {
    public static WebView webView_Favorid;
    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;
    LinearLayout noConnetionLayout;
    LottieAnimationView lottieAnimationView;


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_favorid,container,false);
        webView_Favorid = view.findViewById(R.id.webview_Favorid);
//        progressBar = view.findViewById(R.id.prg_favorid);
        lottieAnimationView = view.findViewById(R.id.favorid_lottie);
        noConnetionLayout = view.findViewById(R.id.layout_no_connection);
        loade_webView();

        swipeRefreshLayout = view.findViewById(R.id.swip_Favorid);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               loade_webView();
               swipeRefreshLayout.setRefreshing(false);
            }
        });

        return view;

    }

    public void loade_webView(){
        webView_Favorid.getSettings().setJavaScriptEnabled(true);
        webView_Favorid.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                progressBar.setVisibility(View.VISIBLE);
                lottieAnimationView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                progressBar.setVisibility(View.GONE);
                lottieAnimationView.setVisibility(View.INVISIBLE);
            }
        });
        checkConnection();
    }


    public void checkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(wifi.isConnected()){
            webView_Favorid.loadUrl("https://khadije.com/delneveshte");
            webView_Favorid.setVisibility(View.VISIBLE);
            noConnetionLayout.setVisibility(View.INVISIBLE);

        }else if(mobile.isConnected()){
            webView_Favorid.loadUrl("https://khadije.com/delneveshte");
            webView_Favorid.setVisibility(View.VISIBLE);
            noConnetionLayout.setVisibility(View.INVISIBLE);

        }else {
            progressBar.setVisibility(View.INVISIBLE);
            webView_Favorid.setVisibility(View.INVISIBLE);
            noConnetionLayout.setVisibility(View.VISIBLE);
        }
    }



}
