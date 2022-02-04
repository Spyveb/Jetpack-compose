package com.example.jetpackcompose.Repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackcompose.Retorfit.ApiClient.getSport
import com.example.jetpackcompose.RetrofitResModel.SportListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sumit5ue.MyVity.Util.dismissProgress

class SportListRepository {

    fun getSportList(): LiveData<SportListResponse> {
        val data = MutableLiveData<SportListResponse>()
        getSport(object : Callback<SportListResponse> {
            override fun onResponse(
                call: Call<SportListResponse>,
                response: Response<SportListResponse>
            ) {
                dismissProgress()
                if (response.body() != null) {
                    data.setValue(response.body())
                } else {
                    data.setValue(null)
                }
            }

            override fun onFailure(call: Call<SportListResponse>, t: Throwable) {
                data.setValue(null)
                Log.e("message", "" + t.message)
                dismissProgress()

            }
        })
        return data
    }
}