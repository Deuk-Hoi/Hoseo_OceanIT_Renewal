package com.deuksoft.hoseooceanit2.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.UserInfoDTO
import com.deuksoft.hoseooceanit2.HTTPManager.RepositoryManager.LoginRepository

class LoginViewModel(application: Application): AndroidViewModel(application) {
    var loginRepository = LoginRepository()

    fun tryLogin(userId: String, userPass: String):LiveData<UserInfoDTO>{
        return loginRepository.tryLogin(userId, userPass)
    }
    fun getMessage(): LiveData<String>{
        return loginRepository.message
    }
}