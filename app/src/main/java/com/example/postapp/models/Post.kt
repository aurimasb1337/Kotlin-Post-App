package com.example.postapp.models

import androidx.room.Entity
import androidx.room.Ignore


data class Post  (
    val userId : String,
    val id : String,
    val title : String? =null,
    val body : String? = null
)
data class PostList(
    val Data: List<List<Post>>,
    val ResponseStatus: Int
)