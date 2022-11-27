package com.example.mvp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvp.R
import com.example.mvp.adapter.PostAdapter
import com.example.mvp.databinding.ActivityMainBinding
import com.example.mvp.model.Post
import com.example.mvp.presentlar.MainPresImpl
import com.example.mvp.presentlar.MainPresenter
import com.example.mvp.presentlar.MainView

class MainActivity : AppCompatActivity(), MainView {
    private val postAdapter by lazy { PostAdapter() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var presenter: MainPresImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter = MainPresImpl(this)
        binding.rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }

        presenter.getAllPostList()
        postAdapter.onDeleteClick = {
            AlertDialog.Builder(this).apply {
                setTitle("Delete")
                setPositiveButton("Yes") {di, _ ->
                    di.dismiss()
                    presenter.deletePost(it.id)
                }
                setNeutralButton("Cancel", null)
            }.create().show()
        }
    }

    override fun onPostListSuccess(posts: List<Post>) {
        postAdapter.submitList(posts)
        binding.progressBar.isVisible = false
    }

    override fun onPostListFailure(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onPostDeleteSuccess(post: Post) {
        Toast.makeText(this,post.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onPostDeleteFailure(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}