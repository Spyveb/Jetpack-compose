package com.example.jetpackcompose.RetrofitResModel


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class SportListResponse(
    val `payload`: ArrayList<payload>,
    val success: Boolean
)

@Parcelize
data class payload(
    val id: Int,
    val name: String,
    val image: String,
) : Parcelable





