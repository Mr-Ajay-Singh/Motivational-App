package com.invictus.common.sharedPreference

import android.content.Context
import android.content.SharedPreferences
import com.chibatching.kotpref.KotprefModel


open class CommonSharedPref() : KotprefModel() {
    override val kotprefName: String = "common_shared_pref"

    open fun registerOnSharedPreferenceChangeListener(listenerPrefValueChange: SharedPreferences.OnSharedPreferenceChangeListener?) {
        this.preferences.registerOnSharedPreferenceChangeListener(listenerPrefValueChange)
    }

    open fun unregisterOnSharedPreferenceChangeListener(listenerPrefValueChange: SharedPreferences.OnSharedPreferenceChangeListener?) {
        listenerPrefValueChange?.let {
            this.preferences.unregisterOnSharedPreferenceChangeListener(listenerPrefValueChange)
        }
    }


    //SharePreference Keys
    var SHERD_PREF_VERSION: Int by intPref(1, key = "common_shared_pref")


}

