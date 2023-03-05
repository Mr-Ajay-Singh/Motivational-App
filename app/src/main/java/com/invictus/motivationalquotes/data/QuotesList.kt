package com.invictus.motivationalquotes.data

import com.invictus.motivationalquotes.data.quotes.BreakupQuotes
import com.invictus.motivationalquotes.data.quotes.ConfidenceQuotes
import com.invictus.motivationalquotes.data.quotes.FriendshipQuotes
import com.invictus.motivationalquotes.data.quotes.KarmaQuotes
import timber.log.Timber

object QuotesList {

    fun getQuotesList():List<String>{
        val list = SelectedTopicsList.getSelectedList()


        val quoteList = arrayListOf<String>()
        var totalEmptyStrings = 0
        list.forEach{
            quoteList.addAll(getQuotesListByTitle(it))
            if(it.isEmpty())totalEmptyStrings++
        }

        if(totalEmptyStrings == list.size){
            quoteList.clear()
            SelectedTopics.values().forEach {
                quoteList.addAll(getQuotesListByTitle(it.value))
            }
        }

        quoteList.removeIf{it.isEmpty()}

        quoteList.shuffle()
        Timber.d("==>data $quoteList")
        return quoteList
    }

    private fun getQuotesListByTitle(str: String): List<String>{

        return when(str){
            SelectedTopics.BREAKUP.value -> BreakupQuotes.getQuotes()
            SelectedTopics.CONFIDENCE.value -> ConfidenceQuotes.getQuotes()
            SelectedTopics.FRIENDSHIP .value -> FriendshipQuotes.getQuotes()
            SelectedTopics.KARMA.value -> KarmaQuotes.getQuotes()
            else -> arrayListOf<String>()
        }

    }
}