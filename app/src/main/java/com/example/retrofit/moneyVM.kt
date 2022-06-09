package com.example.retrofit



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import timber.log.Timber

class moneyVM(var repo : RetroRepo) : ViewModel(){
     var moneyList  = MutableLiveData<List<Money>>()
   // lateinit var moneyAPIList : Observable<List<Money>>

    fun getallAPIMoney(): Observable<List<Money>> {
       // CoroutineScope(Dispatchers.IO).launch {
            return repo.getallAPIMoney()
//            if (res.isSuccessful) {
//                moneyAPIList =  res.body()!!
//            }
      //  }
    }
    fun getallmoney(){
        CoroutineScope(Dispatchers.IO).launch {
            var res = repo.getallMoney()
            if(res.isSuccessful){
                moneyList.postValue( res.body())
            }
        }
    }

    fun insert(requBody: RequestBody){

        CoroutineScope(Dispatchers.IO).launch {
            var res = repo.insert(requBody)
            if(res.isSuccessful){
                val gson = GsonBuilder().setPrettyPrinting().create()
                val pJson = gson.toJson(JsonParser.parseString(res.body().toString()))
                print(pJson)
            }
        }
    }


    fun getall(): @NonNull Observable<List<Money>> {
        Timber.d("timber inside view model")
        Timber.e("timber inside view model error")
        return repo.getall()
    }

    fun  insert(money: Money){
        repo.insertMoney(money)
    }
}