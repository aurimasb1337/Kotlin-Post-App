package com.example.postapp.network

import com.example.postapp.models.Post
import com.example.postapp.models.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Controller {

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>



    @GET("/posts/{id}")
    suspend fun getPostById(
    @Path("id") number: Int
    ): Response<Post>

    @GET("/users/{id}")
    suspend fun getUserById(
        @Path("id") number: Int
    ): Response<User>
}