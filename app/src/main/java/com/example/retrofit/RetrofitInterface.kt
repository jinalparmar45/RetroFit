package com.example.retrofit


import com.google.gson.Gson
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST



interface RetrofitInterface {

    @GET("Data.json")
    fun getAllAPIMoney() : Observable<List<Money>>

    @GET("Data.json")
   suspend fun getAllMoney() : Response<List<Money>> //Call<List<Money>> //Call<JSONObject> // Call<List<Money>>

   @POST("/")
   suspend fun insert(@Body requestBody: RequestBody) : Response<ResponseBody>

   companion object{
        var baseurl = //"https://jsonplaceholder.typicode.com/"
            "https://jinalparmar45.github.io/Data/";
        fun create(): RetrofitInterface{
            val retro = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(baseurl)

                .build()
            return  retro.create(RetrofitInterface::class.java )
        }
    }
}