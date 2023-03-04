package com.invictus.motivationalquotes.db

import android.app.Application
import com.invictus.common.application.CommonApplicationInterface

class MotivationApplication: Application(),CommonApplicationInterface {

    override fun onCreate() {
        super.onCreate()
        initialise(this)
    }

}