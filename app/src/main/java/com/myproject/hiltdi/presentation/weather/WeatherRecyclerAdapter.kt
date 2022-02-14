package com.myproject.hiltdi.presentation.weather

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myproject.hiltdi.R
import com.myproject.hiltdi.model.presentation.WeatherPresentation

class WeatherRecyclerAdapter(private val onItemClicked: (WeatherPresentation) -> Unit) :
    RecyclerView.Adapter<WeatherRecyclerAdapter.ViewHolder>() {

    companion object {
        private const val TAG = "WeatherRecyclerAdapter"
        private const val ICON_BASE_URL = "https://openweathermap.org"
        const val IMAGE_PATH = "img"
        const val W_PATH = "w"
        const val FORMAT_SUFFIX = ".png"
    }

    private val weathers = mutableListOf<WeatherPresentation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_weather_item, parent, false), onItemClicked)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setWeather(weathers[position])
    }

    override fun getItemCount(): Int = weathers.size

    fun addAllWeathers(weathers: List<WeatherPresentation>) {
        this.weathers.clear()
        this.weathers.addAll(weathers)
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View, private val onItemClicked: (WeatherPresentation) -> Unit) : RecyclerView.ViewHolder(view) {

        private var weather: WeatherPresentation? = null

        private val weatherCityText: TextView = view.findViewById(R.id.text_weatherCity)
        private val weatherTemperatureText: TextView = view.findViewById(R.id.text_weatherTemperature)
        private val weatherHumidityText: TextView = view.findViewById(R.id.text_weatherHumidity)
        private val weatherIcon: ImageView = view.findViewById(R.id.icon_weather)

        init {
            view.setOnClickListener {
                weather?.let { nonNullWeather ->
                    onItemClicked(nonNullWeather)
                }
            }
        }

        fun setWeather(weather: WeatherPresentation) {
            this.weather = weather
            view.context?.let { context ->
                val cityText = context.getString(R.string.weather_city, weather.city)
                val temperatureText =
                    context.getString(R.string.weather_temperature, weather.temperature.toString())
                val humidityText = context.getString(R.string.weather_humidity, weather.humidity.toString())

                weatherCityText.text = cityText
                weatherTemperatureText.text = temperatureText
                weatherHumidityText.text = humidityText

                try {
                    val url =
                        "${ICON_BASE_URL}/${IMAGE_PATH}/${W_PATH}/${weather.icon}${FORMAT_SUFFIX}"
                    Glide
                        .with(context)
                        .asBitmap()
                        .load(url)
                        .into(weatherIcon)
                } catch (e: Exception) {
                    Log.e(TAG, e.message ?: "Error during load weather icon by url")
                }
            }
        }
    }
}