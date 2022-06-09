package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    //var list = MutableList<Money>(
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // if(isDebugMode == "Dev" ){
          println("inside main activity :: ${BuildConfig.DEBUG}")
        Timber.d("timber inside main activity")
        //}

    var moneylist :List<Money> = ArrayList<Money>()
         var moneyadapter: MoneyAdapter = MoneyAdapter(moneylist)
        val recyclerview : RecyclerView = findViewById(R.id.Recycler)
        recyclerview.layoutManager = LinearLayoutManager(this)

        recyclerview.adapter = moneyadapter

        val api =  RetrofitInterface.create()
        val repo = RetroRepo(api, this)
        val vm = moneyVM(repo)

//        vm.moneyList.observe(this) {
//            moneyadapter.setItem(it as ArrayList<Money>)
//        }
//
//        vm.getallmoney()


        //insert into DB and get it
       // vm.insert(Money(10,300, "credit", 4))

// observable
//vm.moneyAPIList//.subscribeOn(Scheduler.io())
       // vm.getallAPIMoney()
        var list = ArrayList<Money>();
        vm.getall()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onNext = {
                    item -> list.addAll(item)
                        //moneyadapter.setItem(item as ArrayList<Money>)
                    },
                onComplete ={
                    println("Completed, $list")
                    moneyadapter.setItem(list)
                },

                onError = {
                    e -> println("error :: $e")
                }
                    )
               // println("userlist :: $it")
                //moneyadapter.setItem(it as ArrayList<Money>)
               // list.addAll(it)
               //


//


        var Submit : Button = findViewById(R.id.Submit)
        var amount : EditText = findViewById(R.id.addamount)
        var type : EditText = findViewById(R.id.addtype)
        var sharedin : EditText = findViewById(R.id.addsharedin)

Submit.setOnClickListener{
//    var JSONString = "{" +
//            "\"amount\":"+ amount + "," +
//            "\"type\": \""+ type + "\"," +
//            "\"shared_in\":"+ sharedin  +
//            "}"

    var jsonObject = JSONObject()
    jsonObject.put("amount", amount)
    jsonObject.put("type", type)
    jsonObject.put("shared_in", sharedin)
   // {"amount" : 10,"type": "credit","shared_in":2}
    vm.insert(jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull()))
    }
//    var jsonobj: JsonObject = JsonParser.parseString(JSONString) as JsonObject
//    val reqbody = JSONString.toRequestBody("application/json".toMediaType())
        //RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONString)


}

//        var api =  RetrofitInterface.create().getAllMoney()
//        api.enqueue(object  : Callback<List<Money>>{
//            override fun onResponse(call: Call<List<Money>>, response: Response<List<Money>>) {
//
//                 moneylist = response.body() as ArrayList<Money>
//                moneyadapter.setItem(moneylist as ArrayList<Money>)
//               println("response:: ${response.body()}")
//            }
//
//            override fun onFailure(call: Call<List<Money>>, t: Throwable) {
//                println("response:: $t")
//            }
//        })





    }
