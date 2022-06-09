package com.example.retrofit

import android.content.Context
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.Response.*


@RunWith(JUnit4::class)
class RepoTest {
    lateinit var repo: RetroRepo

    @Mock
    var activity :MainActivity = mock(MainActivity::class.java)
    @Mock
     var mMockContext: Context = mock(Context::class.java)

    @Mock
    lateinit var dao: MoneyDao

    @Mock
    lateinit var inter: RetrofitInterface

    @Mock
    lateinit var AppDatabase : AppDatabase

    @Before()
    fun setup(){
        MockitoAnnotations.openMocks(this)
       // Mockito.`when`(mMockContext.act)
       // Mockito.`when` (mMockContext).thenReturn(Context::class.java)
        repo = RetroRepo(inter, mMockContext)
    }


//    class SomeClassTest {
//        private var mSomeClass: SomeClass? = null
//        private var mLooper: Looper? = null
//        @before
//        fun setup() {
//            mLooper = mock(Looper::class.java)
//            val ctx = mock(Context::class.java)
//            `when`(ctx.mainLooper).thenReturn(mLooper)
//            mSomeClass = SomeClass(mContext)
//        }
//    }
//class SomeClass(mContext: Context) {
//    init {
//        val looper = mContext.mainLooper
//        val mHandler = Handler(looper)
//    }
//}


    @Test
    fun TestGetAllMoney(){
        runBlocking {
            var fakeList = listOf<Money>( Money(123,20,"Credit",6))
            Mockito.`when`( repo.getAllmoney())
                .thenReturn(Observable.just(fakeList))
                //.then(Response.success(fakeList))

            var result = inter.getAllMoney()

            Assert.assertEquals(fakeList, result.body())
//            result?.subscribe {
//
//            }

        }


    }

}

