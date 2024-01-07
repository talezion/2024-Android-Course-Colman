package com.idz.lecture4_demo3.base

import android.app.Application
import android.content.Context

class MyApplication: Application() {

    object Globals {
        var appContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        Globals.appContext = applicationContext
    }
}