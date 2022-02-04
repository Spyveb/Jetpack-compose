package com.example.jetpackcompose.Retorfit

import android.text.TextUtils
import com.example.jetpackcompose.RetrofitResModel.SportListResponse
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    var mRetrofit_Base: Retrofit? = null

    var USER_BASE_URL = "http://54.176.18.73/"

    val retrofitBase: Retrofit?
        get() {
            if (null == mRetrofit_Base) {
                val clientBuilder = OkHttpClient.Builder()
                clientBuilder.connectTimeout(100, TimeUnit.SECONDS)
                clientBuilder.readTimeout(100, TimeUnit.SECONDS)
                clientBuilder.writeTimeout(15, TimeUnit.SECONDS)
                clientBuilder.interceptors().add(ApiLogInterceptor())
                val client = clientBuilder.build()
                val builder = Retrofit.Builder()
                val gson = GsonBuilder()
                    .setLenient()
                    .create()
                builder.baseUrl(if (!TextUtils.isEmpty(USER_BASE_URL)) USER_BASE_URL else USER_BASE_URL)
                builder.addConverterFactory(GsonConverterFactory.create(gson))
                builder.client(client)
                builder.build()
                mRetrofit_Base = builder.build()
            }
            return mRetrofit_Base
        }

    private val apiClientBase: ApiInterface
        get() = retrofitBase!!.create(ApiInterface::class.java)



    fun getSport(listener: Callback<SportListResponse>) {
        apiClientBase.getSport().enqueue(listener)
    }


}