package no.konsultas.uio.forecast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.search_button).setOnClickListener {
            val searchQuery = findViewById<EditText>(R.id.search_bar).text.toString()
            putDummyDataInResultView(searchQuery)
        }
    }

    private fun putDummyDataInResultView(searchQuery: String) {
        findViewById<TextView>(R.id.search_result_city_name).text = searchQuery
        findViewById<TextView>(R.id.search_result_degrees).text = "25"
        findViewById<TextView>(R.id.search_result_description).text = "Sunny"
    }
}