package com.example.retrofit

object SampleTest {
    fun Validations(money: Money): Boolean{
        return !money.type.equals("")
    }
}