package com.example.retrofit

import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("Data.json")
    fun getAllMoney() : Call<List<Money>> //Call<JSONObject> // Call<List<Money>>

    companion object{
        var baseurl = "https://jinalparmar45.github.io/Data/";
        fun create(): RetrofitInterface{
            val retro = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseurl)
                .build()
            return  retro.create(RetrofitInterface::class.java )
        }
    }
}