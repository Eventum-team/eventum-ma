package com.eventum.ma.presenters

import androidx.lifecycle.MutableLiveData
import com.eventum.ma.models.repositories.CustomCallback
import com.eventum.ma.models.repositories.SignInRepository
import com.eventum.ma.presenters.presenters.SignInPresenterInt
import com.eventum.ma.views.views.EventsViewInt
import com.eventum.ma.views.views.SignInInt

class SignInPresenter (var signInView: SignInInt): SignInPresenterInt {
    var tokenAccess : MutableLiveData<String> = MutableLiveData()
    private val signInRepository: SignInRepository = SignInRepository(this)

    override fun verifyAccount(email: String, password: String) {
        signInRepository.verifyAccount(email,password)
    }

    override fun allowAccess(accessToken:String,refreshToken:String) {
        signInView.allowAccess(refreshToken,accessToken) }
    override fun verifyToken(token:String) {
        signInRepository.verifyToken(token,object : CustomCallback<String> {
            override fun onSuccess(token: String?) {
                tokenAccess.postValue(token)
            }
            override fun onFailed(exception: java.lang.Exception) {
            }
        })
    }
    override fun denyAccess() {
        signInView.denyAccess()
    }


}