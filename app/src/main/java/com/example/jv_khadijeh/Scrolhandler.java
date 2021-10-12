package com.example.jv_khadijeh;

import android.view.View;
import android.widget.FrameLayout;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Scrolhandler extends CoordinatorLayout.Behavior<BottomNavigationItemView> {


    public boolean layoutDependsOn(CoordinatorLayout parent , BottomNavigationView child , View dependency){
        return dependency instanceof FrameLayout;
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, BottomNavigationView child, View directTargetChild,View target ,int nestedScrollAxes){
return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;

    }

    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout , BottomNavigationView child, View target , int dx , int dy , int[] consumed){
        if (dy < 0){
            showBottomNavigationView(child);
        }else if (dy >0){
            hideBottomNavigationView(child);
        }
    }

    private void hideBottomNavigationView(BottomNavigationView view){
        view.animate().translationY(view.getHeight());
    }

    private void showBottomNavigationView(BottomNavigationView view){
        view.animate().translationY(0);
    }
}
