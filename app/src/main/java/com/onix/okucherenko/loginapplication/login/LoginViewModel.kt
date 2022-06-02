package com.onix.okucherenko.loginapplication.login

import androidx.lifecycle.ViewModel
class LoginViewModel() : ViewModel() {

    var userName = ""

    fun checkUserName(userName: String): Boolean {
        if (userName.isEmpty() || userName.contains(" ")) {
            return false
        }
        this.userName = userName
        return true
    }

    fun checkPassword(userPassword: String): Int {

        when (userPassword) {
            userPassword.uppercase() -> return 1
            userPassword.lowercase() -> return 2
        }

        if (!userPassword.matches(Regex(".*[0-9]"))) return 3
        if (userPassword.length < 8) return 4
        return 0
    }
}
