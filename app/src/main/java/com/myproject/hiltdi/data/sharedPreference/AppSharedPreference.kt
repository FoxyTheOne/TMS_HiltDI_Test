package com.myproject.hiltdi.data.localStorage.sharedPreference

import android.content.Context
import com.myproject.hiltdi.data.sharedPreference.IAppSharedPreference
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppSharedPreference @Inject constructor(@ApplicationContext context: Context):
    IAppSharedPreference {
    companion object {
        // Константа - имя файла
        private const val PREFERENCE_NAME = "AppSharedPreference"
        // Ключи для данных
        private const val PREFERENCE_EMAIL = "PREFERENCE_EMAIL"
        private const val PREFERENCE_PASSWORD = "PREFERENCE_PASSWORD"
    }

    private val sharedPreference = context.getSharedPreferences( // У нас будет один общий файл, поэтому .getSharedPreferences
        PREFERENCE_NAME,
        Context.MODE_PRIVATE
    )

    override fun saveEmail(email: String) {
        sharedPreference?.edit()?.putString(PREFERENCE_EMAIL, email)?.apply()
    }
    override fun savePassword(password: String) {
        sharedPreference?.edit()?.putString(PREFERENCE_PASSWORD, password)?.apply()
    }

    override fun getEmail(): String = sharedPreference.getString(PREFERENCE_EMAIL, "") ?: ""
    override fun getPassword(): String = sharedPreference.getString(PREFERENCE_PASSWORD, "") ?: ""
}