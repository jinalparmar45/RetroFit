package com.example.retrofit

import org.junit.Assert.assertEquals
import org.junit.Test

class SampleUnitTest {
    @Test
    fun validationcheck1(){
        var money = Money(1,10,"",0)
        assertEquals(false, SampleTest.Validations(money))
    }

    @Test
    fun validationcheck2(){
        var money = Money(1,10,"Type",0)
        assertEquals(true, SampleTest.Validations(money))
    }
}