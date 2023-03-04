package com.invictus.common.application

import android.content.Context
import com.airbnb.mvrx.Mavericks
import com.chibatching.kotpref.Kotpref
import timber.log.Timber

interface CommonApplicationInterface {

    fun initialise(context: Context) {
        Mavericks.initialize(context)
        Kotpref.init(context)
        Timber.plant(Timber.DebugTree())
    }
}