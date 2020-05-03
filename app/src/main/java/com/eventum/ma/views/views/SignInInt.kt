package com.eventum.ma.views.views

interface SignInInt {
    fun verifyAccount(email: String, password: String)
    fun verifyToken(token: String?)

    fun allowAccess(accessToken: String, refreshToken: String)

    fun denyAccess()
}