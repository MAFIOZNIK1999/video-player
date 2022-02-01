package com.example.testtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.adapter.VideoAdapter
import com.example.testtask.common.Common
import com.example.testtask.databinding.ActivityMainBinding
import com.example.testtask.retrofit.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var mService: RetrofitServices
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: VideoAdapter
    private lateinit var videoList: RecyclerView
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mService = Common.retrofitService
        videoList = binding.rvVideos
        videoList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        videoList.layoutManager = layoutManager
        getAllMovieList()
    }

    private fun getAllMovieList() {
        mService.getMovieList().enqueue(object : Callback<com.example.testtask.model.Response> {
            override fun onResponse(
                call: Call<com.example.testtask.model.Response>,
                response: Response<com.example.testtask.model.Response>
            ) {

                adapter = VideoAdapter(
                    baseContext,
                    response.body() as com.example.testtask.model.Response
                )
                adapter.notifyDataSetChanged()
                videoList.adapter = adapter

            }

            override fun onFailure(call: Call<com.example.testtask.model.Response>, t: Throwable) {
                Log.d("TAG", "onFailure: ")
            }

        })
    }
}
