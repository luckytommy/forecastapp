package no.konsultas.uio.forecast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import no.konsultas.uio.forecast.api.ForecastDto
import no.konsultas.uio.forecast.api.OpenWeatherMapApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivityViewModel : ViewModel() {

    val forecastModelLiveData = MutableLiveData<ForecastModel>()

    fun getForecast(cityName: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service: OpenWeatherMapApi = retrofit.create(OpenWeatherMapApi::class.java)
        viewModelScope.launch {
            val result = service.fetchForecastForCity(cityName)
            val forecastModel = ForecastModel(name = result.name, degrees = fromKelvinToCelsius(result.main.temp), description = result.weather.first().main)
            forecastModelLiveData.postValue(forecastModel)
        }
    }

    private fun fromKelvinToCelsius(temp: String): String {
        return "${(temp.toFloat() - 273.15).toInt()}°"
    }
}

data class ForecastModel(val name: String, val degrees: String, val description: String)