package com.myproject.hiltdi.data.localStorage.sharedPreference

interface IAppSharedPreference {
    fun saveEmail(email: String)
    fun savePassword(password: String)

    fun getEmail(): String
    fun getPassword(): String
}