package com.eventum.ma.presenters

import com.eventum.ma.models.models.UserModel
import com.eventum.ma.models.repositories.CustomCallback
import com.eventum.ma.models.repositories.SignUpRepository
import com.eventum.ma.presenters.presenters.SignUpPresenterInt
import com.eventum.ma.views.views.SignUpInt

class SignUpPresenter(private var signUpView: SignUpInt  ): SignUpPresenterInt{

    private var signUpRepository: SignUpRepository = SignUpRepository(this)

    override fun createAccount(user: UserModel) {
       signUpRepository.createAccount(
           user.email,
           user.password,
           user.name,
           user.phone_number,
           user.age,
           user.career,
           "active",
           object : CustomCallback<Boolean> {
               override fun onSuccess(result: Boolean?) {
                   onUserCreated()
               }
               override fun onFailed(exception: java.lang.Exception) {
                   onCreationFailed()
               }
           }
       )
    }

    override fun onUserCreated() {
        signUpView.onUserCreated()
    }

    override fun onCreationFailed() {
        signUpView.onCreationFailed()
    }

}
