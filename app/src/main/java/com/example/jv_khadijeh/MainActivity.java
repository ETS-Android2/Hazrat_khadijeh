package com.example.jv_khadijeh;

import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNa;
    ProgressBar progressBar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNa = findViewById(R.id.bottom_navigation);
        bottomNa.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                Fragment selectfragment = null;

                switch (item.getItemId()){
                    case R.id.nav_home:
                        selectfragment = new HomeFragment();
                        break;
                    case R.id.nav_favorid:
                        selectfragment = new FavoridFragment();
                        break;
                    case R.id.nav_search:
                        selectfragment = new SearchFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectfragment).commit();
                return true;
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        bottomNa.setSelectedItemId(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        if (HomeFragment.webView_home.canGoBack()) {
            HomeFragment.webView_home.goBack();

        }else if(FavoridFragment.webView_Favorid.canGoBack()){
            FavoridFragment.webView_Favorid.goBack();
        }
        else {
            finish();
        }
    }
}