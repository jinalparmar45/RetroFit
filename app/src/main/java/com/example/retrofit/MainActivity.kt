package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    var moneylist :List<Money> = ArrayList<Money>()
         var moneyadapter: MoneyAdapter = MoneyAdapter(moneylist)
        val recyclerview : RecyclerView = findViewById(R.id.Recycler)
        recyclerview.layoutManager = LinearLayoutManager(this)

        recyclerview.adapter = moneyadapter

        var api =  RetrofitInterface.create().getAllMoney()
        api.enqueue(object  : Callback<List<Money>>{
            override fun onResponse(call: Call<List<Money>>, response: Response<List<Money>>) {

                 moneylist = response.body() as ArrayList<Money>
                moneyadapter.setItem(moneylist as ArrayList<Money>)
               println("response:: ${response.body()}")
            }

            override fun onFailure(call: Call<List<Money>>, t: Throwable) {
                println("response:: $t")
            }
        })





    }
}