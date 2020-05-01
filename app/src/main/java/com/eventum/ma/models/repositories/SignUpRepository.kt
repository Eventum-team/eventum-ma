package com.eventum.ma.models.repositories

import com.eventum.ma.models.models.UserModel
import com.eventum.ma.presenters.presenters.SignUpPresenterInt

class SignUpRepository(var signUpPresenter: SignUpPresenterInt){
    fun createAccount(user: UserModel){
        if(user.password == "123456"){
            signUpPresenter.onUserCreated()
        }else{
            signUpPresenter.onCreationFailed()
        }
    }


}