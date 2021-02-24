package no.konsultas.uio.forecast

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import no.konsultas.uio.forecast.api.ForecastDto
import no.konsultas.uio.forecast.api.OpenWeatherMapApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.search_button).setOnClickListener {
            val searchQuery = findViewById<EditText>(R.id.search_bar).text.toString()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            val service: OpenWeatherMapApi = retrofit.create(OpenWeatherMapApi::class.java)
            GlobalScope.launch {
                val result = service.fetchForecastForCity(searchQuery)
                populateViews(result)
            }
        }
    }

    private fun populateViews(result: ForecastDto) {
        Handler(Looper.getMainLooper()).post {
            findViewById<TextView>(R.id.search_result_city_name).text = result.name
            findViewById<TextView>(R.id.search_result_degrees).text = result.main.temp
            findViewById<TextView>(R.id.search_result_description).text = result.weather.first().main
        }
    }
}