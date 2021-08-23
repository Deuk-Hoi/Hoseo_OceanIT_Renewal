package com.deuksoft.hoseooceanit2.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.deuksoft.hoseooceanit2.HTTPManager.DTOManager.UserInfoDTO
import com.deuksoft.hoseooceanit2.R
import com.deuksoft.hoseooceanit2.SaveInfoManager.UserInfo
import com.deuksoft.hoseooceanit2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var loginBinding: ActivityLoginBinding
    lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginBinding.apply {
            lifecycleOwner = this@LoginActivity
            loginViewModel = loginViewModel
        }
        loginBinding.loginBtn.setOnClickListener {
            tryLogin()
        }
    }

    private fun tryLogin(){
        loginViewModel.tryLogin(loginBinding.userId.text.toString(), loginBinding.userPassword.text.toString()).observe(this){
            if(UserInfo(this).setUserInfo(it)){
                var intent = Intent()
                intent.putExtra("response", it)
                setResult(100, intent)
                finish()
            }
        }
        loginViewModel.getMessage().observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}