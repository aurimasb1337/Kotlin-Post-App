package com.example.postapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postapp.adapters.CustomAdapter
import com.example.postapp.models.MainViewModel
import com.example.postapp.models.MainViewModelFactory
import com.example.postapp.models.Post
import com.example.postapp.models.User
import com.example.postapp.repository.Repository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item.*


class ItemActivity : AppCompatActivity() {

    private lateinit var  viewModel: MainViewModel
    private lateinit var repository : Repository
    private lateinit var viewModelFactory : MainViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

         repository = Repository()
         viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


        val postId:String = intent.getStringExtra("id").toString()
        fetchPostDetails(postId.toInt())

    }

    private fun fetchPostDetails(id : Int) {
       // Toast.makeText(application, "bundle" + id, Toast.LENGTH_SHORT).show()


        viewModel.getPostById(id)
        viewModel.myPost.observe(this, Observer { response ->

            if(response.isSuccessful){
              //  Log.d("Response", response.body().toString())
                var tmpPost : Post = response.body() as Post
                postTitle.text = tmpPost.title
                postBody.text = tmpPost.body

              fetchUserDetails(tmpPost.userId.toInt())
    }
})

    }
    private fun fetchUserDetails(id : Int) {
       Picasso.get().load("https://source.unsplash.com/collection/542909/?sig=${id}").into(profilePic)

        viewModel.getUserById(id)
        viewModel.mUser.observe(this, Observer { response ->

            if(response.isSuccessful){
                var tmpUser : User = response.body() as User
            userName.text= tmpUser.name


            }
        })



      //  Log.d("Title", tmpPost.title.toString())
    }

    }
