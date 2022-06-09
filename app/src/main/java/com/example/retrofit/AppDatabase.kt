package com.example.retrofit

import android.content.Context
import androidx.room.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Database(entities = [Money::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun MoneyDao(): MoneyDao
    companion object {
        var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase? {
            if(INSTANCE == null) {
                //6 - we are acquiring an instance of RoomDB builder
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java, "money.db")
                        .allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}

@Dao
interface MoneyDao {
    @Query("select * from money")
    fun selectmoney(): Single<List<Money>>

    @Insert
    fun insert(money : Money)
}