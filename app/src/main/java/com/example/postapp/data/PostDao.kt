package com.example.postapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.postapp.models.Post

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addPost(post: PostEntity)

    @Query("SELECT * FROM  post_table ORDER BY id ASC")
    fun readAllPosts() : LiveData<List<PostEntity>>
}