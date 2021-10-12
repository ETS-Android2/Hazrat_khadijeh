package com.example.jv_khadijeh.api;

import com.example.jv_khadijeh.model.Data;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("posts")
    Call<Data> getPost();
}
