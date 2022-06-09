package com.example.retrofit

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "money")
data class Money (
    @PrimaryKey
    var id: Int,
    var amount : Int,
var type: String,
var shared_in: Int
        )