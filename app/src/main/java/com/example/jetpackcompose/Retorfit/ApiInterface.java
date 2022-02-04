package com.example.jetpackcompose.Retorfit;


import com.example.jetpackcompose.RetrofitResModel.SportListResponse;
import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiInterface {


    @GET("api/sportList")
    Call<SportListResponse> getSport();


}