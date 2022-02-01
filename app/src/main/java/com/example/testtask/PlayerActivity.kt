package com.example.testtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testtask.adapter.VideoAdapter
import com.example.testtask.databinding.ActivityPlayerBinding
import com.example.testtask.model.Category
import com.example.testtask.model.Video
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val player = ExoPlayer.Builder(this).build()
        binding.playerView.player = player
//        val mediaItem = MediaItem.fromUri()
    }
}
