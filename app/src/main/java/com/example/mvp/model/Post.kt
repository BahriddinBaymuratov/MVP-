package com.example.mvp.model

import retrofit2.http.Body

data class Post(
    val id:Int,
    val userId:Int,
    val title:String,
    val body: String
)