package no.konsultas.uio.forecast

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel by viewModels<MainActivityViewModel>()
        viewModel.forecastModelLiveData.observe(this) { forecastModel ->
            populateViews(forecastModel)
        }

        findViewById<View>(R.id.search_button).setOnClickListener {
            val searchQuery = findViewById<EditText>(R.id.search_bar).text.toString()
            viewModel.getForecast(searchQuery)
        }
    }

    private fun populateViews(forecastModel: ForecastModel) {
        findViewById<TextView>(R.id.search_result_city_name).text = forecastModel.name
        findViewById<TextView>(R.id.search_result_degrees).text = forecastModel.degrees
        findViewById<TextView>(R.id.search_result_description).text = forecastModel.description
    }
}