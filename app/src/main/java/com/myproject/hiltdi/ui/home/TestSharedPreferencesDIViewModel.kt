package com.myproject.hiltdi.ui.home

import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.hiltdi.data.localStorage.sharedPreference.IAppSharedPreference
import com.myproject.hiltdi.data.network.weather.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TestSharedPreferencesDIViewModel: ViewModel() {
    companion object {
        private const val TAG = "PreferencesViewModel"
    }

    fun saveEmailAndPasswordSharedPreference(
        sharedPreference: IAppSharedPreference,
        editTextEmail: AppCompatEditText,
        editTextPassword: AppCompatEditText
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val emailText = editTextEmail.text.toString()
            val passwordText = editTextPassword.text.toString()

            if (emailText.isNotBlank() && passwordText.isNotBlank()) {
                sharedPreference.saveEmail(email = emailText)
                sharedPreference.savePassword(password = passwordText)
            }
        }
    }

    fun showEmailAndPasswordSharedPreference(
        sharedPreference: IAppSharedPreference,
        textEmailAndPasswordView: AppCompatTextView
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val emailText = sharedPreference.getEmail()
            val passwordText = sharedPreference.getPassword()

            if (emailText.isNotBlank() && passwordText.isNotBlank()) {
                val textToShow = "Saved email: $emailText, saved password: $passwordText"

                viewModelScope.launch(Dispatchers.Main) {
                    textEmailAndPasswordView.text = textToShow
                }

            }
        }
    }

    fun getWeather(weatherService: WeatherService) {
        viewModelScope.launch(Dispatchers.IO) {
            val weatherInMinsk = weatherService.getWeatherInMinsk().toString()
            Log.i(TAG, "Weather service launched")
            Log.i(TAG, weatherInMinsk)
            Log.i(TAG, "Weather service finished")
        }
    }
}