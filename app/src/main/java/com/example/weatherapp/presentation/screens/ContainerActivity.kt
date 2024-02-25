package com.example.weatherapp.presentation.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityContainerBinding

////// TODO Dependencies Injection(ГОТОВО)
// TODO Custom View с графиком температуры по часам
// TODO Смена темы и/или шрифта и/или картинки-фона через SharedPreferences
////// TODO Перевод на два языка(ГОТОВО)
// TODO sealed Class State
// TODO Тестирование
////// TODO !!В БД должны перезаписываться дни, а не добавляться повторы!!(ГОТОВО)
// TODO Кнопка поиска и ре фреш
// TODO Кнопка поиска - голосовой ввод???
// TODO Картинки под разные разрешения
// TODO Версии зависимостей в Gradle-файле в константы
// TODO Несколько макетов в RecyclerView(дни недели над плашками погоды)
class ContainerActivity : AppCompatActivity() {

    private val binding: ActivityContainerBinding by lazy {
        ActivityContainerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        launchSplashFragment()
    }

    private fun launchSplashFragment() {
        // TODO разобраться с помещением и удалением фрагментов в backStack - чтобы из WeekFragment выходить
        //  из приложения
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.activity_container, SplashFragment.newInstance())
//            .addToBackStack(Constants.SPLASH_FRAGMENT_NAME)
            .commit()
    }
}