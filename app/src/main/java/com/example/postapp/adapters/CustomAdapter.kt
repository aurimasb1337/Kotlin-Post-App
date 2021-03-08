package com.example.postapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.postapp.ItemActivity
import com.example.postapp.MainActivity
import com.example.postapp.R
import com.example.postapp.models.Post
import com.google.android.material.internal.ContextUtils.getActivity
import java.security.AccessController.getContext


class CustomAdapter(private val dataSet: ArrayList<Post>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.title);

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position].title
        viewHolder.textView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(viewHolder.textView.context,dataSet[position].id, Toast.LENGTH_SHORT).show()
                val intent = Intent(viewHolder.textView.context, ItemActivity::class.java)
                intent.putExtra("id", dataSet[position].id)
                viewHolder.textView.context.startActivity(intent)
            }})

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
