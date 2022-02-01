package com.example.testtask.model

data class Video(
    val sources: MutableList<String>,
    val description: String,
    val subtitle: String,
    val thumb: String,
    val title: String
)
