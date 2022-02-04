package com.example.jetpackcompose.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.coaching.NetworkManager.isConnected
import com.example.jetpackcompose.Adapter.SportListAdapter
import com.example.jetpackcompose.R
import com.example.jetpackcompose.Viewmodel.SportListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import sumit5ue.MyVity.Util.showProgress
import sumit5ue.MyVity.Util.toast

class MainActivity : AppCompatActivity() {

    private val sportListViewModel: SportListViewModel by lazy {
        ViewModelProvider(this).get(SportListViewModel::class.java)
    }

    private lateinit var sportListAdapter: SportListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvList.layoutManager = LinearLayoutManager(this)


        getSportList()

        sportListAdapter = SportListAdapter(onClick = { name ->

            toast(this, name)

        })

    }

    private fun getSportList() {

        if (!isConnected) {
            toast(this, resources.getString(R.string.error_no_internet))
            return
        }

        showProgress(this)

        sportListViewModel.getSportList()
            .observeForever {
                if (it?.payload != null && it.payload .size != 0){
                    if (it.success){
                        sportListAdapter.swapList(it.payload)
                        rvList.adapter = sportListAdapter
                    }else{
                        toast(this, resources.getString(R.string.data_not_found))
                    }
                }else{
                    toast(this, resources.getString(R.string.data_not_found))
                }
            }
    }
}