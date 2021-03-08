package com.example.postapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class PostEntity (
    @PrimaryKey(autoGenerate = true)
    val userId : Int,
    val id : String,
    val title : String? =null,
    var body : String? = null
) {

}