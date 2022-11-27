package com.example.mvp.presentlar

import com.example.mvp.model.Post
import com.example.mvp.network.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresImpl(private val mainView: MainView): MainPresenter {

    override fun getAllPostList() {
        RetroInstance.retroInstance().getAllPosts().enqueue(object : Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful){
                    mainView.onPostListSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                mainView.onPostListFailure(t.message!!)
            }
        })
    }

    override fun deletePost(id: Int) {
        RetroInstance.retroInstance().deletePost(id).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful){
                    mainView.onPostDeleteSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                mainView.onPostDeleteFailure(t.message!!)
            }
        })
    }
}