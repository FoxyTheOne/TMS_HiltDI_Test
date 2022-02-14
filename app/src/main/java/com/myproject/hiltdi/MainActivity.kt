package com.myproject.hiltdi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.myproject.hiltdi.presentation.home.TestSharedPreferencesDIFragment
import com.myproject.hiltdi.presentation.weather.WeatherFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Открываем первый фрагмент
        if (savedInstanceState == null) {
            openFragment(WeatherFragment())
        }
    }

    override fun onBackPressed() {
        val fragmentCount = supportFragmentManager.backStackEntryCount

        if (fragmentCount > 1) {
            super.onBackPressed()
        } else {
            finish()
        }
    }

    fun openFragment(fragment: Fragment, doClearBackStack: Boolean = false) { // Задаём параметр по умолчанию - если не передать true для очистки стека, по умолчанию будет false
        if (doClearBackStack){ // Если передали true, чистим стек
            clearBackStack()
        }

        // Открываем фрагмент из Activity:
        supportFragmentManager.beginTransaction()
            // Добавляем фрагмент в main_fragment_container, с тегом (название фрагмента)). Тег не обязателен. Тег можно использовать при поиске в стеке в дальнейшем
            .replace(R.id.main_fragment_container, fragment, fragment.toString())
            // Добавляем в BackStack, чтобы оставался в стеке фрагментов и при клике "назад" этот фрагмент открылся, как предыдущий
            .addToBackStack(null)
            // Исполнить транзакцию:
            .commit()
    }

    private fun clearBackStack() = supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
}