package com.eventum.ma.presenters.presenters

interface SignInPresenterInt {
    fun verifyAccount(email:String,password:String)

    fun allowAccess()

    fun denyAccess()
}