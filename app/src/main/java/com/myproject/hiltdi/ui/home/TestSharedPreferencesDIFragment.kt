package com.myproject.hiltdi.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.myproject.hiltdi.R
import com.myproject.hiltdi.data.localStorage.sharedPreference.IAppSharedPreference
import com.myproject.hiltdi.data.network.weather.WeatherService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TestSharedPreferencesDIFragment: Fragment() {
    private lateinit var editTextEmail: AppCompatEditText
    private lateinit var editTextPassword: AppCompatEditText
    private lateinit var buttonSave: Button
    private lateinit var buttonShow: Button
    private lateinit var textEmailAndPasswordView: AppCompatTextView

    @Inject
    lateinit var sharedPreference: IAppSharedPreference
    @Inject
    lateinit var weatherService: WeatherService

    private lateinit var viewModel: TestSharedPreferencesDIViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_home_test_shared_preferences, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[TestSharedPreferencesDIViewModel::class.java]

        editTextEmail = view.findViewById(R.id.editText_email)
        editTextPassword = view.findViewById(R.id.editText_password)
        buttonSave = view.findViewById(R.id.button_save)
        buttonShow = view.findViewById(R.id.button_show)
        textEmailAndPasswordView = view.findViewById(R.id.text_emailAndPasswordView)

        buttonSave.setOnClickListener {
            viewModel.saveEmailAndPasswordSharedPreference(
                sharedPreference,
                editTextEmail,
                editTextPassword)
        }

        buttonShow.setOnClickListener {
            viewModel.showEmailAndPasswordSharedPreference(
                sharedPreference,
                textEmailAndPasswordView
            )
        }

        viewModel.getWeather(weatherService)
    }
}