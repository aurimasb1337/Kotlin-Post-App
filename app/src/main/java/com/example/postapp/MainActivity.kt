package com.example.postapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postapp.adapters.CustomAdapter
import com.example.postapp.data.PostEntity
import com.example.postapp.models.MainViewModel
import com.example.postapp.models.MainViewModelFactory
import com.example.postapp.models.Post
import com.example.postapp.models.PostViewModel
import com.example.postapp.repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

private lateinit var  viewModel: MainViewModel
    private lateinit var  postViewModel: PostViewModel
    private var  postList = ArrayList<Post>()
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
        postViewModel= ViewModelProvider(this).get(PostViewModel::class.java)
        readSavedData()
            evaluteConnection()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
    }



    private fun readSavedData(){
        postList = ArrayList<Post>()


        postViewModel.readAllData.observe(this, Observer {
                post ->
            for(p in post){
                var tmppost : Post = Post(p.userId.toString(), p.id, p.title, p.body)
                postList.add(tmppost)

                val obj_adapter = CustomAdapter(postList)
                recyclerView.adapter = obj_adapter
            }

        })

    }

    private fun callApi() {
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPosts()
        viewModel.myPosts.observe(this, Observer { response ->
            if(response.isSuccessful){
                // Log.d("Response", response.body().toString())


                var tmpPostList = ArrayList<Post>()

                tmpPostList = response.body() as ArrayList<Post>

                for(p in tmpPostList){
                      var postEntity : PostEntity = PostEntity(p.id.toInt(), p.id, p.title, p.body)

                    postViewModel.addPost(postEntity)
                }
                val obj_adapter = CustomAdapter(tmpPostList)
                recyclerView.adapter = obj_adapter




            }
        })

    }



    @RequiresApi(Build.VERSION_CODES.M)
    private fun evaluteConnection() {
       if(!isOnline(application)){
           showNoInternetDialog()
           refreshLayout.setOnRefreshListener {

               refreshLayout.isRefreshing = false
           }
       }
        else{
           refreshLayout.setOnRefreshListener {
               callApi();
               refreshLayout.isRefreshing = false
           }
       callApi()
       }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun showNoInternetDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("No internet connection")
        builder.setMessage("We have a message")


        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(applicationContext,
               "Retry", Toast.LENGTH_SHORT).show()
            evaluteConnection()
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(applicationContext,
              "Cancel", Toast.LENGTH_SHORT).show()
        }


        builder.show()
    }


    // Boilerplate code
    @RequiresApi(Build.VERSION_CODES.M)
    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }




}
