package no.konsultas.uio.forecast.api

import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "3ecbb5b4359c257b82c1ddf5d182b005"

interface OpenWeatherMapApi {

    @GET("data/2.5/weather")
    suspend fun fetchForecastForCity(@Query("q") cityName: String, @Query("appid") appId: String = API_KEY): ForecastDto
}

data class ForecastDto(val name: String, val main: Main, val weather: List<Weather>)

data class Main(val temp: String)

data class Weather(val main: String)