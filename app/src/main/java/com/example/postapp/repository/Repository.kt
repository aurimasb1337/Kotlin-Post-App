package com.example.postapp.repository

import com.example.postapp.models.Post
import com.example.postapp.models.User
import com.example.postapp.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class Repository {
    suspend fun getPosts() :  Response<List<Post>> {
        return RetrofitInstance.controller.getPosts()
    }
    suspend fun getPostById(id:Int): Response<Post>{
        return RetrofitInstance.controller.getPostById(id)
    }

    suspend fun getUserByid(id:Int): Response<User>{
        return RetrofitInstance.controller.getUserById(id)
    }
}