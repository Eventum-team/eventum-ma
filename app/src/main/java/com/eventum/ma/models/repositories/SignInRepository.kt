package com.eventum.ma.models.repositories

import android.widget.Toast
import com.eventum.ma.presenters.SignInPresenter

class SignInRepository(var signInPresenter: SignInPresenter){
    fun verifyAccount(email:String,password:String){
        if(password == "123456"){
            signInPresenter.allowAccess()
        }else{
            signInPresenter.denyAccess()
        }
    }


}