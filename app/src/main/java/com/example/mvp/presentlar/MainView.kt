package com.example.mvp.presentlar

import com.example.mvp.model.Post

interface MainView {

    fun onPostListSuccess(posts: List<Post>)
    fun onPostListFailure(error: String)

    fun onPostDeleteSuccess(post: Post)
    fun onPostDeleteFailure(error: String)
}