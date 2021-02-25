package no.konsultas.uio.forecast

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import no.konsultas.uio.forecast.api.ForecastDto
import no.konsultas.uio.forecast.api.Main
import no.konsultas.uio.forecast.api.OpenWeatherMapApi
import no.konsultas.uio.forecast.api.Weather
import org.junit.Test

import org.junit.Rule

internal class MainActivityViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `ForecastDTO is mapped correctly to model`() {
        val mainActivityViewModel = MainActivityViewModel(MockApi())

        val expectedModel = ForecastModel("test name", "6°", "Cloudy")

        mainActivityViewModel.forecastModelLiveData.observeForever {
            assert(it.equals(expectedModel)) {
                "Expected $expectedModel, but was $it"
            }
        }

        mainActivityViewModel.getForecast("dummy")
    }

    class MockApi : OpenWeatherMapApi {
        override suspend fun fetchForecastForCity(cityName: String, appId: String): ForecastDto {
            val weatherList = listOf(Weather("Cloudy"))
            val main = Main("280")
            val name = "test name"
            return ForecastDto(name, main, weatherList)
        }
    }
}