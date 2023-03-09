package com.invictus.motivationalquotes.db

import android.content.SharedPreferences
import com.invictus.common.sharedPreference.CommonSharedPref

object MotivationSharedPreferences: CommonSharedPref() {

    override fun registerOnSharedPreferenceChangeListener(listenerPrefValueChange: SharedPreferences.OnSharedPreferenceChangeListener?) {
        this.preferences.registerOnSharedPreferenceChangeListener(listenerPrefValueChange)
    }

    override fun unregisterOnSharedPreferenceChangeListener(listenerPrefValueChange: SharedPreferences.OnSharedPreferenceChangeListener?) {
        listenerPrefValueChange?.let {
            this.preferences.unregisterOnSharedPreferenceChangeListener(listenerPrefValueChange)
        }
    }

    var SELECTED_IMAGE: Int by intPref(0, key = "selected_image")
    var TEXT_SIZE: Int by intPref(16, key = "text_size")
    var TEXT_COLOR: Int by intPref(0, key = "text_color")
    var SELECTED_TOPICS: String by stringPref("", key = "selected_topics")
    var FIRST_TIME_USER: Boolean by booleanPref(true, key = "first_time_user")
}