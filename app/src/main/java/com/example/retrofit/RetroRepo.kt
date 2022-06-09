package com.example.retrofit

import android.content.Context
import androidx.lifecycle.LiveData
import com.google.gson.JsonObject
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import okhttp3.RequestBody

class RetroRepo(var repo : RetrofitInterface, context: Context) {
   suspend fun getallMoney() =   repo.getAllMoney()
    var db: MoneyDao? = AppDatabase.getInstance(context)?.MoneyDao()


    fun getallAPIMoney() = repo.getAllAPIMoney()

   suspend fun insert(reqBody: RequestBody) = repo.insert(reqBody)

    fun getAllmoney() : Observable<List<Money>>? {
        return db?.selectmoney()?.toObservable()
    }
    fun getall(): @NonNull Observable<List<Money>> {
       return  Observable.concatArray(
            getallAPIMoney(),
            getAllmoney()
        )


    }

    fun insertMoney(money: Money){
        db?.insert(money)
    }


}