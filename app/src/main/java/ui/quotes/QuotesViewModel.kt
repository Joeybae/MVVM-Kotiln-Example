package ui.quotes

import androidx.lifecycle.ViewModel
import data.Quote
import data.QuoteRepository

class QuotesViewModel (private val quoteRepository: QuoteRepository)
: ViewModel() {

    fun getQuotes() = quoteRepository.getQuotes()

    fun addQuote(quote: Quote) = quoteRepository.addQuote(quote)
}