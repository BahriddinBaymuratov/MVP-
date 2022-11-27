package com.example.mvp.network

import com.example.mvp.model.Post
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("Content-type:application/json")

    @GET("posts")
    fun getAllPosts():  Call<List<Post>>

    @DELETE("posts/{id}")
    fun deletePost(
        @Query("id") id:Int
    ): Call<Post>
}