package com.example.testtask.retrofit

import com.example.testtask.model.Response
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("videos")
    fun getMovieList(): Call<Response>
}
