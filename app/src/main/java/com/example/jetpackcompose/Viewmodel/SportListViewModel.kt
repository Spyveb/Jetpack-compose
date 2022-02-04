package com.example.jetpackcompose.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.jetpackcompose.Repositories.SportListRepository
import com.example.jetpackcompose.RetrofitResModel.SportListResponse


class SportListViewModel() :  ViewModel() {
    private val  sportListRepository = SportListRepository()


    fun getSportList(): LiveData<SportListResponse> {
        return sportListRepository.getSportList()
    }

}