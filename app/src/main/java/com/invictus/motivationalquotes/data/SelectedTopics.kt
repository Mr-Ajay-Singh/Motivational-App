package com.invictus.motivationalquotes.data

import com.invictus.motivationalquotes.db.MotivationSharedPreferences
import timber.log.Timber

enum class SelectedTopics(val value: String) {
    FRIENDSHIP("Friendship"),
    BREAKUP("Break up"),
    KARMA("Karma"),
    CONFIDENCE("Confidence"),
    BEAUTY("Beauty"),
    DREAMS("Dreams"),
    FOCUS("Focus"),
    LIFE("Life"),
    LOVE("Love"),
    MOTIVATION("Motivation"),
    STAY_STRONG("Stay Strong"),
    WISDOM("Wisdom"),
}

object SelectedTopicsList{

    var selectedTopic = ""

    fun getList():List<String>{
        val categorySelection = arrayListOf<String>()
        SelectedTopics.values().forEach {
            categorySelection.add(it.value)
        }
        return categorySelection
    }

    fun getSelectedList(): List<String> {
        val selectedString = MotivationSharedPreferences.SELECTED_TOPICS
        selectedTopic = selectedString
        Timber.d("==>list ${selectedString.split("#")}")
        return selectedString.split("#")
    }
}