package ui.quotes

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.quotesactivity.R
import data.Quote
import kotlinx.android.synthetic.main.activity_quotes.*
import utilities.InjectorUtils
import java.util.*

class QuotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)
        initializeUi()

        //calendar
        val mDateField = findViewById<TextView>(R.id.fieldDate)
        val selectDate = findViewById<Button>(R.id.btnDate)
        selectDate.setOnClickListener {
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            var datePickerDialog = DatePickerDialog(this@QuotesActivity, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                //                                date.setText(day + "/" + (month + 1) + "/" + year);
                mDateField.text = year.toString() + "/" + (month + 1) + "/" + day
            }, year, month, dayOfMonth)
            datePickerDialog.show()
        }
    }

    private fun initializeUi() {
        // Get the QuotesViewModelFactory with all of it's dependencies constructed
        val factory = InjectorUtils.provideQuotesViewModelFactory()
        // Use ViewModelProviders class to create / get already created QuotesViewModel
        // for this view (activity)
        val viewModel = ViewModelProviders.of(this, factory)
            .get(QuotesViewModel::class.java)

        // Observing LiveData from the QuotesViewModel which in turn observes
        // LiveData from the repository, which observes LiveData from the DAO ☺
        /*viewModel.getQuotes().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach { quote ->
                stringBuilder.append("$quote\n\n")
            }
            textView_quotes.text = stringBuilder.toString()
        })*/

        // When button is clicked, instantiate a Quote and add it to DB through the ViewModel
        button_add_quote.setOnClickListener {
            val quote = Quote(editText_quote.text.toString(), editText_author.text.toString(), fieldDate.text.toString())
            viewModel.addQuote(quote)
            editText_quote.setText("")
            editText_author.setText("")
            fieldDate.setText("")
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}
