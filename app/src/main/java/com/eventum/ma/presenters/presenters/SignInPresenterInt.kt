package com.eventum.ma.presenters.presenters

interface SignInPresenterInt {
    fun verifyAccount(email:String,password:String)

    fun verifyToken(token:String)

    fun allowAccess(accessToken:String,refreshToken:String)

    fun denyAccess()
}