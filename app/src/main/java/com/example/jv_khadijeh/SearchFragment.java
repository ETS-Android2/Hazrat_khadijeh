package com.example.jv_khadijeh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jv_khadijeh.api.ApiClient;
import com.example.jv_khadijeh.api.ApiService;
import com.example.jv_khadijeh.model.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    TextView tv_fragSearch ;
//    Context context;
    LinearLayout noConnectionLayout;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    ApiService apiService;



    public SearchFragment() {
//        this.context = context;
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.listView_search_frag);
        noConnectionLayout = view.findViewById(R.id.layout_no_connection);
        checkConnection();


//        Gson gson = new Gson();
//        Data data = gson.fromJson(JsonValue.post, Data.class);
//        for (int i = 0; i < data.result.size(); i++) {
//            Log.e("qqq", "onCreateView: " + data.result.get(i).title );
////        }


//        apiService = ApiClient.getClient().create(ApiService.class);
//        Call<Data> getApiPost = apiService.getPost();
//        getApiPost.enqueue(new Callback<Data>() {
//            @Override
//            public void onResponse(Call<Data> call, Response<Data> response) {
////                Log.e("qqq", "onResponse: "+response.body().ok);
//                recyclerView.setAdapter(new AdapterSearch(getContext(),response.body().result));
//
//            }
//            @Override
//            public void onFailure(Call<Data> call, Throwable t) {
//                Log.e("qqqq", "onFailure: ",t );
//
//            }
//        });

        return view;
    }

    @SuppressLint("ResourceAsColor")
    public void checkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(wifi.isConnected()){
            apiService = ApiClient.getClient().create(ApiService.class);
        Call<Data> getApiPost = apiService.getPost();
        getApiPost.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
//                Log.e("qqq", "onResponse: "+response.body().ok);
                recyclerView.setAdapter(new AdapterSearch(getContext(),response.body().result));

            }
            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.e("qqqq", "onFailure: ",t );

            }
        });

        }else if(mobile.isConnected()){
            apiService = ApiClient.getClient().create(ApiService.class);
        Call<Data> getApiPost = apiService.getPost();
        getApiPost.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
//                Log.e("qqq", "onResponse: "+response.body().ok);
                recyclerView.setAdapter(new AdapterSearch(getContext(),response.body().result));

            }
            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.e("qqqq", "onFailure: ",t );

            }
        });

        }else {
            recyclerView.setVisibility(View.INVISIBLE);
            noConnectionLayout.setVisibility(View.VISIBLE);
        }
    }

}
