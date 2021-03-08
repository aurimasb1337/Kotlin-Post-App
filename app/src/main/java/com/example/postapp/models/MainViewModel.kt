package com.example.postapp.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postapp.data.PostDatabase
import com.example.postapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {
    val myPosts : MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val myPost : MutableLiveData<Response<Post>> = MutableLiveData()
    val mUser : MutableLiveData<Response<User>> = MutableLiveData()


    fun getPostById(id: Int){
        viewModelScope.launch {
            val response : Response<Post> = repository.getPostById(id)
            myPost.value = response
        }
    }

    fun getUserById(id: Int){
        viewModelScope.launch {
            val response : Response<User> = repository.getUserByid(id)
            mUser.value = response
        }
    }


    fun getPosts() {
            viewModelScope.launch {
                val response = repository.getPosts()
                myPosts.value=response
            }
    }
    }
