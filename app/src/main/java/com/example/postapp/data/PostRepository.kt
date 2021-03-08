package com.example.postapp.data

import androidx.lifecycle.LiveData
import com.example.postapp.models.Post

class PostRepository(private val postDao: PostDao) {
                val readAllData : LiveData<List<PostEntity>> = postDao.readAllPosts()

    suspend fun addPost(post: PostEntity){
        postDao.addPost(post)
    }
}