package com.example.testtask.common

import com.example.testtask.retrofit.RetrofitClient
import com.example.testtask.retrofit.RetrofitServices

object Common {
    private val BASE_URL = "https://raw.githubusercontent.com/DavidStdn/NitrixTestTask/main/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}
