package com.myproject.hiltdi.presentation.home

import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myproject.hiltdi.data.sharedPreference.IAppSharedPreference
import com.myproject.hiltdi.data.networkService.WeatherService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestSharedPreferencesDIViewModel @Inject constructor(
    var sharedPreference: IAppSharedPreference,
    var weatherService: WeatherService
): ViewModel() {
    companion object {
        private const val TAG = "PreferencesViewModel"
    }

    fun saveEmailAndPasswordSharedPreference(
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

    fun getWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            val weatherInMinsk = weatherService.getWeather().toString()
            Log.i(TAG, "Weather service launched")
            Log.i(TAG, weatherInMinsk)
            Log.i(TAG, "Weather service finished")
        }
    }
}