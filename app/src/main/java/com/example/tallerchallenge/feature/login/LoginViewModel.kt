package com.example.tallerchallenge.feature.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private var username: String = ""
    private var password: String = ""

    val isLoginEnabled = MutableLiveData(false)

    val errorMessage = MutableLiveData<String?>(null)

    val navigateToWelcome = MutableLiveData(false)

    fun onUsernameChange(value: String) {
        this.username = value
        this.errorMessage.value = null
        this.isLoginEnabled.value = isLoginEnabled()
    }

    fun onPasswordChange(value: String) {
        this.password = value
        this.errorMessage.value = null
        this.isLoginEnabled.value = isLoginEnabled()
    }

    fun onLoginClick() {
        if (username == LOGIN_MOCK && password == PASSWORD_MOCK) {
            navigateToWelcome.value = true
        } else {
            errorMessage.value = "Username or password incorrect"
        }
    }

    private fun isLoginEnabled(): Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }

    companion object {
        private const val LOGIN_MOCK = "username"
        private const val PASSWORD_MOCK = "123456"
    }
}