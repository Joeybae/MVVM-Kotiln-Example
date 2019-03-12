package data

data class Quote(val quoteText: String,
                 val author: String,
                 val Date : String) {

    override fun toString(): String {
        return "Title : $quoteText \n Author : $author \n Date : $Date"
    }
}