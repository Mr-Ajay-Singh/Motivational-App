package com.invictus.motivationalquotes.data

import com.invictus.motivationalquotes.data.quotes.*
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
            quoteList.addAll(getQuotesListByTitle(SelectedTopics.MOTIVATION.value))
        }

        quoteList.removeIf{it.isEmpty()}

        quoteList.shuffle()
        Timber.d("==>data $quoteList")
        return quoteList
    }

    private fun getQuotesListByTitle(str: String): List<String>{

        return when(str){
            SelectedTopics.BEAUTY.value -> BeautyQuotes.getQuotes()
            SelectedTopics.BREAKUP.value -> BreakupQuotes.getQuotes()
            SelectedTopics.CONFIDENCE.value -> ConfidenceQuotes.getQuotes()
            SelectedTopics.DREAMS.value -> DreamsQuotes.getQuotes()
            SelectedTopics.FOCUS.value -> FocusQuotes.getQuotes()
            SelectedTopics.FRIENDSHIP.value -> FriendshipQuotes.getQuotes()
            SelectedTopics.KARMA.value -> KarmaQuotes.getQuotes()
            SelectedTopics.LIFE.value -> LifeQuotes.getQuotes()
            SelectedTopics.LOVE.value -> LoveQuotes.getQuotes()
            SelectedTopics.MOTIVATION.value -> MotivationQuotes.getQuotes()
            SelectedTopics.STAY_STRONG.value -> StayStrongQuotes.getQuotes()
            SelectedTopics.WISDOM.value -> WisdomQuotes.getQuotes()
            else -> arrayListOf<String>()
        }

    }
}