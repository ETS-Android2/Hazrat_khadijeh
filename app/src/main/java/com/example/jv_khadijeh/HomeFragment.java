package com.example.jv_khadijeh;

import android.content.Context;
import android.graphics.Bitmap;
import android.icu.text.CaseMap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ChasingDots;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.Pulse;
import com.github.ybq.android.spinkit.style.RotatingCircle;
import com.github.ybq.android.spinkit.style.Wave;

public class HomeFragment extends Fragment {
    public static WebView webView_home;
    SwipeRefreshLayout swipeRefreshLayout ;
    ProgressBar progressBar;
    LinearLayout noConnectLayout;


//    public HomeFragment(WebView webView) {
//        this.webView_home = webView;
//    }
//
//    public HomeFragment(int contentLayoutId, WebView webView) {
//        super(contentLayoutId);
//        this.webView = webView;
//    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);
        webView_home = view.findViewById(R.id.webview_Homepage);
//        progressBar = view.findViewById(R.id.progress);
        progressBar = view.findViewById(R.id.spin_kit);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);

        noConnectLayout = view.findViewById(R.id.layout_no_connection);
        load_webview();

        swipeRefreshLayout = view.findViewById(R.id.swip_Home);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                webView.reload();
               load_webview();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }


    public void load_webview(){
        webView_home.getSettings().setJavaScriptEnabled(true);
        webView_home.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
                super.onPageFinished(view, url);
            }
        });

        checkConnection();
        webView_home.goBack();
   }


    public void checkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(wifi.isConnected()){
            webView_home.loadUrl("https://khadije.com/fa");
            webView_home.setVisibility(View.VISIBLE);
            noConnectLayout.setVisibility(View.INVISIBLE);

        }else if(mobile.isConnected()){
            webView_home.loadUrl("https://khadije.com/fa");
            webView_home.setVisibility(View.VISIBLE);
            noConnectLayout.setVisibility(View.INVISIBLE);

        }else {
            progressBar.setVisibility(View.INVISIBLE);
            webView_home.setVisibility(View.INVISIBLE);
            noConnectLayout.setVisibility(View.VISIBLE);
        }
    }

}
