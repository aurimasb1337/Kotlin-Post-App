package com.example.postapp.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postapp.data.PostDatabase
import com.example.postapp.data.PostEntity
import com.example.postapp.data.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel (application: Application) : AndroidViewModel(application){

     val readAllData: LiveData<List<PostEntity>>
    private val repository : PostRepository

    init{

        val postDao = PostDatabase.getDatabase(application).postDao()
        repository = PostRepository(postDao)
        readAllData = repository.readAllData
    }
    fun addPost(post: PostEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPost(post)
        }
    }
}